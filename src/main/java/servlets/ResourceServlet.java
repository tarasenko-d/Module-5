package servlets;

import resources.TestResource;
import sax.ReadXMLFileSAX;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ResourceServlet extends HttpServlet {

    private TestResource testResource;

    public ResourceServlet(TestResource testResource) {
        this.testResource = testResource;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {

        response.getWriter().println("Servlet");
        response.setStatus(HttpServletResponse.SC_OK);

        final String path = request.getParameter("path");

        readFile(path);

        readXML(path);
    }

    private void readXML(String path){
        TestResource resource = (TestResource) ReadXMLFileSAX.readXML(path);
        assert resource != null;
        testResource.setAge(resource.getAge());
        testResource.setName(resource.getName());

    }

    private void readFile(String filePath) {
        try (RandomAccessFile aFile = new RandomAccessFile(filePath, "rw")) {

            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(64);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.clear();
                bytesRead = inChannel.read(buf);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


