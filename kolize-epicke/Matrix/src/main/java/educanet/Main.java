package educanet;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

public class Main {

    public static void main(String[] args) throws Exception {
        //region: Window init
        GLFW.glfwInit();
        // Tell GLFW what version of OpenGL we want to use.
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);

        // Create the window...
        // We can set multiple options with glfwWindowHint ie. fullscreen, resizability etc.
        long window = GLFW.glfwCreateWindow(900, 700, "brm brm", 0, 0);
        if (window == 0) {
            GLFW.glfwTerminate();
            throw new Exception("zadny okynko sorry");
        }
        GLFW.glfwMakeContextCurrent(window);

        // Tell GLFW, that we are using OpenGL
        GL.createCapabilities();
        GL33.glViewport(0, 0, 900, 700);

        // Resize callback
        GLFW.glfwSetFramebufferSizeCallback(window, (win, w, h) -> {
            GL33.glViewport(0, 0, w, h);
        });
        //endregion

        // Main game loop
        Game.init(window);

        // Draw in polygon mod
        //GL33.glPolygonMode(GL33.GL_FRONT_AND_BACK, GL33.GL_LINE);
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Key input management
            if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_F) == GLFW.GLFW_PRESS)
                GLFW.glfwSetWindowShouldClose(window, true); // Send a shutdown signal...

            // Change the background color
            GL33.glClearColor(0f, 0.5f, 0.7f, 0f);
            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);

            Game.render(window);
            Game.update(window);

            // Swap the color buffer -> screen tearing solution
            GLFW.glfwSwapBuffers(window);
            // Listen to input
            GLFW.glfwPollEvents();
        }

        // Don't forget to cleanup
        GLFW.glfwTerminate();
    }

}