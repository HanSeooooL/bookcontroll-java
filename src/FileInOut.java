import java.io.*;

public class FileInOut {
    void addbook(Book newone) {
        try {
            OutputStream dir = new FileOutputStream("/Users/hanseol/HelloWorld/data.txt");
            byte[] by = newone.getBookname().getBytes();
            byte[] sharp = "#".getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getWriter().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getCompany().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = new byte[]{(byte) (newone.getdiditRent() ? 1 : 0)};
            dir.write(by);
            dir.write(sharp);
            by = newone.getRentname().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getRentday().getBytes();
            dir.write(by);
            dir.write(sharp);
            by = newone.getReturnday().getBytes();
            dir.write(by);
            sharp = "\n".getBytes();
            dir.write(sharp);

        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    void fileRead() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/hanseol/HelloWorld/data.txt"));
        while(true) {
            String line = br.readLine();
            if(line == null) break;
            System.out.println(line);
        }
        br.close();
    }


}
