
/*
* The MIT License (MIT)
*
* Copyright (c) 2014 Matthew 'siD' Van der Bijl
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/
package engine.obj;

import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * OBJloader class. Loads in Wavefront .obj file in to the program.
 *
 * @author Matthew Van der Bijl
 */
public class OBJLoader extends Object {

    /**
     * Constructs a new <code>OBJLoader</code>.
     */
    public OBJLoader() {
        super();
    }

    public int createDisplayList(Obj model) {
        int displayList = GL11.glGenLists(1);
        GL11.glNewList(displayList, GL_COMPILE);
        {
            this.render(model);
        }
        GL11.glEndList();
        return displayList;
    }

    /**
     * Renders a given <code>engine.obj.Obj</code> file.
     *
     * @param model the <code>engine.obj.Obj</code> file to be rendered
     */
    public void render(Obj model) {
        GL11.glMaterialf(GL_FRONT, GL_SHININESS, 120);
        GL11.glBegin(GL_TRIANGLES);
        {
            for (Obj.Face face : model.getFaces()) {
                Vector3f[] normals = {
                    model.getNormals().get(face.getNormals()[0] - 1),
                    model.getNormals().get(face.getNormals()[1] - 1),
                    model.getNormals().get(face.getNormals()[2] - 1)
                };
                Vector2f[] texCoords = {
                    model.getTextureCoordinates().get(face.getTextureCoords()[0] - 1),
                    model.getTextureCoordinates().get(face.getTextureCoords()[1] - 1),
                    model.getTextureCoordinates().get(face.getTextureCoords()[2] - 1)
                };
                Vector3f[] vertices = {
                    model.getVertices().get(face.getVertices()[0] - 1),
                    model.getVertices().get(face.getVertices()[1] - 1),
                    model.getVertices().get(face.getVertices()[2] - 1)
                };
                {
                    GL11.glNormal3f(normals[0].getX(), normals[0].getY(), normals[0].getZ());
                    GL11.glTexCoord2f(texCoords[0].getX(), texCoords[0].getY());
                    GL11.glVertex3f(vertices[0].getX(), vertices[0].getY(), vertices[0].getZ());
                    GL11.glNormal3f(normals[1].getX(), normals[1].getY(), normals[1].getZ());
                    GL11.glTexCoord2f(texCoords[1].getX(), texCoords[1].getY());
                    GL11.glVertex3f(vertices[1].getX(), vertices[1].getY(), vertices[1].getZ());
                    GL11.glNormal3f(normals[2].getX(), normals[2].getY(), normals[2].getZ());
                    GL11.glTexCoord2f(texCoords[2].getX(), texCoords[2].getY());
                    GL11.glVertex3f(vertices[2].getX(), vertices[2].getY(), vertices[2].getZ());
                }
            }
        }
        GL11.glEnd();
    } public void render(Obj model, float x, float y, float z) {
        GL11.glMaterialf(GL_FRONT, GL_SHININESS, 120);
        GL11.glBegin(GL_TRIANGLES);
        {
            for (Obj.Face face : model.getFaces()) {
                Vector3f[] normals = {
                        model.getNormals().get(face.getNormals()[0] - 1),
                        model.getNormals().get(face.getNormals()[1] - 1),
                        model.getNormals().get(face.getNormals()[2] - 1)
                };
                Vector2f[] texCoords = {
                        model.getTextureCoordinates().get(face.getTextureCoords()[0] - 1),
                        model.getTextureCoordinates().get(face.getTextureCoords()[1] - 1),
                        model.getTextureCoordinates().get(face.getTextureCoords()[2] - 1)
                };
                Vector3f[] vertices = {
                        model.getVertices().get(face.getVertices()[0] - 1),
                        model.getVertices().get(face.getVertices()[1] - 1),
                        model.getVertices().get(face.getVertices()[2] - 1)
                };
                {
                    GL11.glNormal3f(normals[0].getX(), normals[0].getY(), normals[0].getZ());
                    GL11.glTexCoord2f(texCoords[0].getX(), texCoords[0].getY());
                    GL11.glVertex3f(vertices[0].getX()+x, vertices[0].getY()+y, vertices[0].getZ()+z+6);
                    GL11.glNormal3f(normals[1].getX(), normals[1].getY(), normals[1].getZ());
                    GL11.glTexCoord2f(texCoords[1].getX(), texCoords[1].getY());
                    GL11.glVertex3f(vertices[1].getX()+x, vertices[1].getY()+y, vertices[1].getZ()+z+6);
                    GL11.glNormal3f(normals[2].getX(), normals[2].getY(), normals[2].getZ());
                    GL11.glTexCoord2f(texCoords[2].getX(), texCoords[2].getY());
                    GL11.glVertex3f(vertices[2].getX()+x, vertices[2].getY()+y, vertices[2].getZ()+z+6);
                }
            }
        }
        GL11.glEnd();
    }

    /**
     * @param file the file to be loaded
     * @return the loaded <code>engine.obj.Obj</code>
     * @throws java.io.FileNotFoundException thrown if the engine.obj.Obj file is not found
     */
    public Obj loadModel(File file) throws FileNotFoundException {
        return this.loadModel(new Scanner(file));
    }

    /**
     * @param stream the stream to be loaded
     * @return the loaded <code>engine.obj.Obj</code>
     */
    public Obj loadModel(InputStream stream) {
        return this.loadModel(new Scanner(stream));
    }

    /**
     * @param sc the <code>engine.obj.Obj</code> to be loaded
     * @return the loaded <code>engine.obj.Obj</code>
     */
    public Obj loadModel(Scanner sc) {
        Obj model = new Obj();
        while (sc.hasNextLine()) {
            String ln = sc.nextLine();
            if (ln == null || ln.equals("") || ln.startsWith("#")) {
            } else {
                String[] split = ln.split(" ");
                switch (split[0]) {
                    case "v":
                        model.getVertices().add(new Vector3f(
                                Float.parseFloat(split[1]),
                                Float.parseFloat(split[2]),
                                Float.parseFloat(split[3])
                        ));
                        break;
                    case "vn":
                        model.getNormals().add(new Vector3f(
                                Float.parseFloat(split[1]),
                                Float.parseFloat(split[2]),
                                Float.parseFloat(split[3])
                        ));
                        break;
                    case "vt":
                        model.getTextureCoordinates().add(new Vector2f(
                                Float.parseFloat(split[1]),
                                Float.parseFloat(split[2])
                        ));
                        break;
                    case "f":
                        model.getFaces().add(new Obj.Face(
                                new int[]{
                                    Integer.parseInt(split[1].split("/")[0]),
                                    Integer.parseInt(split[2].split("/")[0]),
                                    Integer.parseInt(split[3].split("/")[0])
                                },
                                new int[]{
                                    Integer.parseInt(split[1].split("/")[1]),
                                    Integer.parseInt(split[2].split("/")[1]),
                                    Integer.parseInt(split[3].split("/")[1])
                                },
                                new int[]{
                                    Integer.parseInt(split[1].split("/")[2]),
                                    Integer.parseInt(split[2].split("/")[2]),
                                    Integer.parseInt(split[3].split("/")[2])
                                }
                        ));
                        break;
                    case "s":
                        model.setSmoothShadingEnabled(!ln.contains("off"));
                        break;
                    default:
                        System.err.println("[OBJ] Unknown Line: " + ln);
                }
            }
        }
        sc.close();
        return model;
    }
}
