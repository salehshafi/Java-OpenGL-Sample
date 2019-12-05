package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.GLCapabilities;


/**
 * SimpleJOGL.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class SimpleJOGL implements GLEventListener {
    
    
    private static GLCapabilities caps;
 
     
    public static void main(String[] args) {
        Frame frame = new Frame("Simple JOGL Application");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new SimpleJOGL());
        
        
        caps=new GLCapabilities();
        caps.setDoubleBuffered(true);
        caps.setSampleBuffers(false);
        
        

        
        
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
         drawable.setAutoSwapBufferMode(true);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

      public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        // Move the "drawing cursor" around
        //gl.glTranslatef(-1.5f, 0.0f, -3.0f);
        //glTranslatef(0.5, 0.5, 0.5);
        gl.glTranslatef(-0.75f, 0.75f, -2.4f);
        gl.glTranslatef(xPos, 0, 0);
        gl.glTranslatef(0, yPos, 0);
        gl.glTranslatef(0, 0, zPos);
        gl.glRotatef(xRot, 1, 0, 0);
        gl.glRotatef(yRot, 0, 1, 0);
        gl.glRotatef(zRot, 0, 0, 1);

        gl.glTranslatef(-0.3f, 0.4f, 0);

//    for(int g=0;g<5;g++)
//    for(int h =0; h<10; h++)
        for (int i = 0; i < 10; i++) {

            gl.glTranslatef(0.2f, 0, 0);
            for (int j = 0; j < 10; j++) 
            {
                gl.glTranslatef(0, -0.2f, 0);
                draw(gl);
           // glTranslatef(0,-0.2,0);
                //  draw();
                // glTranslatef(0,-0.2,0);
                // draw();
                // glTranslatef(0,-0.2,0);
                //  draw();
                //  glTranslatef(0,-0.2,0);
                //  draw();     
            }
            gl.glTranslatef(0, 2, 0);
        }
        gl.glPopMatrix();
        // gl.glFlush();

    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }

    public void OldCode(GL gl) {

        // Drawing Using Triangles
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(1.0f, 0.0f, 0.0f);    // Set the current drawing color to red
        gl.glVertex3f(0.0f, 1.0f, 0.0f);   // Top
        gl.glColor3f(0.0f, 1.0f, 0.0f);    // Set the current drawing color to green
        gl.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
        gl.glColor3f(0.0f, 0.0f, 1.0f);    // Set the current drawing color to blue
        gl.glVertex3f(1.0f, -1.0f, 0.0f);  // Bottom Right
        // Finished Drawing The Triangle
        gl.glEnd();

        // Move the "drawing cursor" to another position
        gl.glTranslatef(3.0f, 0.0f, 0.0f);
        // Draw A Quad
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue
        gl.glVertex3f(-1.0f, 1.0f, 0.0f);  // Top Left
        gl.glVertex3f(1.0f, 1.0f, 0.0f);   // Top Right
        gl.glVertex3f(1.0f, -1.0f, 0.0f);  // Bottom Right
        gl.glVertex3f(-1.0f, -1.0f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();

        // Flush all drawing operations to the graphics card
    }

    public static float xRot = 0.0f;
    public static float yRot = 0.0f;
    public static float zRot = 0.0f;
    public static float xPos = 0.0f;
    public static float yPos = 0.0f;
    public static float zPos = 0.0f;

    void draw(GL gl) {

        gl.glColor3f(1.0f, 0.0f, 0.0f);
        //xRot=SimpleGLJPanel.//
        gl.glBegin(GL.GL_QUADS);

        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, -.1f, 0);
        gl.glVertex3f(-.1f, -.1f, 0);
        gl.glVertex3f(-.1f, 0, 0);
        gl.glEnd();

        //Back
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_QUADS);

        gl.glVertex2f(0, 0);//, -.1);
        gl.glVertex2f(0, -.1f);//, -.1);
        gl.glVertex2f(-.1f, -.1f);//, -.1);
        gl.glVertex2f(-.1f, 0);//, -.1);
        gl.glEnd();

        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glBegin(GL.GL_QUADS);

        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, -.1f, 0);
        gl.glVertex3f(0, 0, -.1f);
        gl.glVertex3f(0, -.1f, -.1f);
        gl.glEnd();

//    //Right
        gl.glColor3f(1.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_QUADS);

        gl.glVertex3f(-.1f, 0, 0);
        gl.glVertex3f(-.1f, -.1f, 0);
        gl.glVertex3f(-.1f, 0, -.1f);
        gl.glVertex3f(-.1f, -.1f, -.1f);
        gl.glEnd();
//
//    //Up

        gl.glColor3f(1.0f, 0.0f, 1.0f);
        gl.glBegin(GL.GL_QUADS);

        gl.glVertex3f(0, -.1f, 0);
        gl.glVertex3f(-.1f, -.1f, 0);
        gl.glVertex3f(-.1f, -.1f, -.1f);
        gl.glVertex3f(0, -.1f, -.1f);
        gl.glEnd();

//    //Down
        gl.glColor3f(0.0f, 1.0f, 1.0f);
        gl.glBegin(GL.GL_QUADS);

        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(-.1f, 0, 0);
        gl.glVertex3f(-.1f, 0, -.1f);
        gl.glVertex3f(0, 0, -.1f);
        gl.glEnd();
    }

}

