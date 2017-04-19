

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dhruvi
 */
public class Compiler_file extends HttpServlet {

     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // String filec = "try.c";
        File outputFile = null;
        Compile_main c = new Compile_main();
      //  Compile_main1 c1 = new Compile_main1();
        //  String filecpp = "try.cpp";
          // String filejava = "try.java";
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
                    String file1 = request.getParameter("param");
                            if(file1.contains("stdio.h")){
                                    //out.println("its a c file");
                outputFile = new File(getServletContext().getRealPath("/")+"First.c");
               //outputFile = new File("//home//dhruvi//NetBeansProjects//temp_carolina//src//java//First.c");
               
           }
           else
               if(file1.contains("iostream")){
               //out.println("its a cpp file");
               outputFile = new File(getServletContext().getRealPath("/")
            + "First.cpp");
           }
            else
               if(file1.contains("public static void main")){
               //out.println("its a java file");
               outputFile = new File(getServletContext().getRealPath("/")
            + "First.java");
               
               }
           // String file2 = request.getParameter("q2");
          //  out.println("--->"+file1);
          //  File outputFile = new File(getServletContext().getRealPath("/")
            //+ "First.c");
          
           
             //System.out.println("---->"+outputFile.getAbsolutePath());
            //  Compile_main obj = new Compile_main();
              //"//home//dhruvi//NetBeansProjects//temp_carolina//build//web//First.txt"
             // System.out.println("in file!!!");
                     // obj.compileCFile("first.c");
                     // String output= obj.runCFile("first.c");
                     // System.out.println("****---->"+output);
                      //out.println("-->"+output);
                       
                 
      FileWriter fout = new FileWriter(outputFile);
      fout.write(file1);         
              
       System.out.println("---->"+outputFile.getPath());
           if (file1.contains("stdio.h")) {
               
               String error = c.compileCFile(file1);
                String output =c.runCFile("First.c");
                
                if(error != null)
                    out.println("---->"+error);
               else
                    out.println("-->"+output);
                
                
               // out.println("c program file");
               // c1.read();
            } 
          else if (file1.contains("iostream")) {
              String error = c.compileCPPFile(file1);
                String output =c.runCPPFile("First.cpp");
                
                if(error != null)
                    out.println("---->"+error);
               else
                    out.println("-->"+output);
                
              
                
            } else if (file1.contains("public static void main")) {
                String error= c.compileJavaFile(file1);
                String output= c.runJavaFile("First.java");
                  if(error != null)
                    out.println("---->"+error);
                  else
                    out.println("-->"+output);
                
            }
           
            fout.close();
    
        
    }
    }

  
    }   

