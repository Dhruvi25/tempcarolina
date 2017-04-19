
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import static java.lang.System.out;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dhruvi
 */
public class Compile_main {
//     String compileJavaFile(String fileName) throws FileNotFoundException, IOException
//    {
//        File f = new File("First.java");
//        byte b[] = fileName.getBytes();
//        OutputStream os = new FileOutputStream(f);
//        os.write(b);
//        String eline = "";
//        //String compileFileCommand = "javac " + fileName;
//        try
//        {
//            System.out.println("Executing Java File");
//
//            Process compileProcess = Runtime.getRuntime().exec("javac " +f);
//
//            String line = "";
//            
//            BufferedReader bri = new BufferedReader(new InputStreamReader(compileProcess.getInputStream()));
//            BufferedReader bre = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
//            while ((line = bri.readLine()) != null)
//            {
//                System.out.println(line);
//            }
//            bri.close();
//            while ((eline = bre.readLine()) != null)
//            {
//                System.out.println(line);
//               
//            }
//            bre.close();
//            if(eline != null )
//                return eline;
//            compileProcess.waitFor();
//            System.out.println("Done.");
//        } catch (Exception e)
//        {
//            // TODO: handle exception
//            System.out.println("Exception ");
//            System.out.println(e.getMessage());
//        }
//        return eline;
//    }
//
//    String runJavaFile(String fileName)
//    {
//        String runFileCommand = "java " + fileName.split(".java")[0];
//        String line="";
//        try
//        {
//            System.out.println("runFileCommand : " + runFileCommand);
//            System.out.println("Running Java File");
//
//            Process runProcess = Runtime.getRuntime().exec(runFileCommand);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
//            line = reader.readLine();
//            System.out.println("line = " + line);
//            while (line != null)
//            {
//                return line;
//            }
//
//        } catch (Exception e)
//        {
//            // TODO: handle exception
//            System.out.println("Exception ");
//            System.out.println(e.getMessage());
//        }
//        return "heee";
//    }
     String compileJavaFile(String fileName) throws FileNotFoundException, IOException
    {
        File f = new File ("First.java");
       byte b[] = fileName.getBytes();
       OutputStream os = new FileOutputStream(f);
       os.write(b);
       // String compileFileCommand = "g++ " + fileName;
        String resultString = "";
        String outputCompile="";
        try
        {

            System.out.println("Compiling CPP File");

            Process processCompile = Runtime.getRuntime().exec("javac " +f);

            BufferedReader brCompileError = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
            String errorCompile = brCompileError.readLine();
            if (errorCompile != null)
            {
                System.out.println("Error Compiler = " + errorCompile);
                 resultString += errorCompile +"\n";
                out.println(errorCompile);
            }
           

            BufferedReader brCompileRun = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
            outputCompile = brCompileRun.readLine();
            if (outputCompile != null){
                System.out.println("Output Compiler = " + outputCompile);
                resultString += outputCompile +"\n";
                return resultString;  
            }
        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
        }
        return null;
    }

    String runJavaFile(String fileName)
    {
      
        String runFileCommand = "java " + fileName.split(".java")[0];
          String outputRun = "";
        try
        {
            System.out.println("Running Java File");

            Process processRun = Runtime.getRuntime().exec(runFileCommand);

            BufferedReader brRun = new BufferedReader(new InputStreamReader(processRun.getErrorStream()));
            String errorRun = brRun.readLine();
            if (errorRun != null)
            {
                System.out.println("Error Run = " + errorRun);
                return errorRun;
            }
            BufferedReader brResult = new BufferedReader(new InputStreamReader(processRun.getInputStream()));
             outputRun = brResult.readLine();
            if (outputRun != null)
               // System.out.println("Output Run = " + outputRun);
                return outputRun;

        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
        }
        return null;
    }
    
   
  
    
    String compileCFile(String fileName) throws IOException {
        ///home/dhruvi/NetBeansProjects/temp_carolina/src/java/
        System.out.println("inside compiling c function  "+fileName);
        String path = "//home//dhruvi//NetBeansProjects//temp_carolina//src//java//";
        ////home//dhruvi//glassfish-4.1//glassfish//domains//domain1//config//
        File f = new File("First.c");
        System.out.println("compile file name1 : "+f.getPath());
        byte b[] = fileName.getBytes();
         OutputStream os = new FileOutputStream(f);
          os.write(b);
          
      //  FileWriter fout = new FileWriter(f);
       //     fout.write(fileName); 
       System.out.println("compile file name2 : "+f.getAbsolutePath());
        String resultString = "";
        String outputCompile="";
        try {
            System.out.println("Compiling C File");
            Process processCompile = Runtime.getRuntime().exec("gcc " +f);
            BufferedReader brCompileError = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
            String errorCompile = brCompileError.readLine();
            if(errorCompile != null) {
                System.out.println("Error Compiler = " + errorCompile);
                out.println(errorCompile);
            }
            resultString += errorCompile + "\n";
            BufferedReader brCompileRun = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
            outputCompile = brCompileRun.readLine();
            if (outputCompile != null) {
                System.out.println("Output Compiler = " + outputCompile);
                resultString += outputCompile + "\n";
                return outputCompile;
                  
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
        }
        return null;
    }

    String runCFile(String fileName) {
       //String runFileCommand = "./" + fileName.split(".c")[0];
	String runFileCommand ="./a.out";
        String line="null";
        String outputRun = "";
        try {

            System.out.println("Running C File");

            Process processRun = Runtime.getRuntime().exec(runFileCommand);

            BufferedReader brRun = new BufferedReader(new InputStreamReader(processRun.getErrorStream()));
            String errorRun = brRun.readLine();
            if (errorRun != null) {
                System.out.println("Error Run = " + errorRun);
            }

            BufferedReader brResult = new BufferedReader(new InputStreamReader(processRun.getInputStream()));
            outputRun = brResult.readLine();
            if (outputRun != null) {
               
                return outputRun;}
                else
                return "no output";
                //System.out.println("Output Run = " + outputRun);
            
            //return outputRun;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
        }
        
        return "heeee";
    }
    String compileCPPFile(String fileName) throws FileNotFoundException, IOException
    {
        File f = new File ("First.cpp");
       byte b[] = fileName.getBytes();
       OutputStream os = new FileOutputStream(f);
       os.write(b);
       // String compileFileCommand = "g++ " + fileName;
        String resultString = "";
        String outputCompile="";
        try
        {

            System.out.println("Compiling CPP File");

            Process processCompile = Runtime.getRuntime().exec("g++ " +f);

            BufferedReader brCompileError = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
            String errorCompile = brCompileError.readLine();
            if (errorCompile != null)
            {
                System.out.println("Error Compiler = " + errorCompile);
                out.println(errorCompile);
            }
            resultString += errorCompile +"\n";

            BufferedReader brCompileRun = new BufferedReader(new InputStreamReader(processCompile.getErrorStream()));
            outputCompile = brCompileRun.readLine();
            if (outputCompile != null){
                System.out.println("Output Compiler = " + outputCompile);
                resultString += outputCompile +"\n";
                return outputCompile;  
            }
        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
        }
        return null;
    }

    String runCPPFile(String fileName)
    {
       // String runFileCommand = "./" + fileName.split(".cpp")[0];
          String runFileCommand = "./a.out";
          String outputRun = "";
        try
        {
            System.out.println("Running CPP File");

            Process processRun = Runtime.getRuntime().exec(runFileCommand);

            BufferedReader brRun = new BufferedReader(new InputStreamReader(processRun.getErrorStream()));
            String errorRun = brRun.readLine();
            if (errorRun != null)
                System.out.println("Error Run = " + errorRun);

            BufferedReader brResult = new BufferedReader(new InputStreamReader(processRun.getInputStream()));
             outputRun = brResult.readLine();
            if (outputRun != null)
               // System.out.println("Output Run = " + outputRun);
                return outputRun;

        } catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Exception ");
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
