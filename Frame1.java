
/*References:-
1. https://www.geeksforgeeks.org/naive-algorithm-for-pattern-searching/
2. https://www.geeksforgeeks.org/boyer-moore-algorithm-for-pattern-searching/
3. https://www.mkyong.com/java/how-do-calculate-elapsed-execute-time-in-java/
4. https://www.geeksforgeeks.org/boyer-moore-algorithm-for-pattern-searching/
5. https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
6. http://www.vogella.com/tutorials/JavaIO/article.html
7. https://www.experts-exchange.com/questions/25010014/Splitting-a-paragraph-into-sentences.html
8. http://www.vogella.com/tutorials/JavaIO/article.html
9. https://github.com/NishadKumar/string-matching-plagiarism-detect/blob/master/Plagiarism%20Detection_report.pdf
10. http://www.java2s.com/Code/Java/Data-Type/Escapespecialcharacterwitha.htm
*/

import java.awt.Frame;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VK-Viven.
 */
public class Frame1 extends javax.swing.JFrame {

  public static String read(String fileName)
    {
        String line = null;
        String text = "";
     try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) 
            {  
                text=text+line;  
            } 
            bufferedReader.close(); 
        }
        catch(FileNotFoundException ex) {        }
        catch(IOException ex) {                 }   
     return text; 
    }
  
public static File[] extract(String path, String file)
{ 
File folder = new File(path);      
File[] Files1 = folder.listFiles();
int a=0;
for(int i =0; i<Files1.length;i++)
{
if(Files1[i].getName().equals(file))
{
Files1[i]=null;
a=i;
}
}
int nSize = Files1.length - 1;
    File[] newFiles = new File[nSize];
    if (nSize > 0) {
        if(a>0){
        System.arraycopy(Files1, 0, newFiles, 0, a);
        System.arraycopy(Files1, a+1, newFiles, a,Files1.length-a-1);
        }
        else if(a==0)
        {
        System.arraycopy(Files1, 1, newFiles, 0,Files1.length - 1);
        }
    }
    return newFiles;
    } 

public static String[] split(String s2)
{ 
String[] Sentence = s2.split("(?<=[a-z])\\.\\s+");
return Sentence;
}
    
public void print(String[] s3)
{
int length = s3.length;
for(int i=0;i<length;i++)
{
    String sentence1 = s3[i];
    display.append("\nSentence:"+i+":"+ sentence1);
}
}

public void listFilesForFolder(final File folder) 
{
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
            display.append("\n"+fileEntry.getName());
        }
    }
}  

public static boolean KMP(String[] words1, String[] words2)
{
        boolean a = false;
        String[] string = words1;
        String[] string1 = words2;
        int M = string1.length;
        int N = string.length;
        int pref[] = new int[M];
        int j = 0;  // index for pat[]
        // Preprocess the pattern 
        LPSArray(string1,M,pref);
 
        int i = 0;  // index for txt[]
        while (i < N)
        {//display.appendln(pat[j]+" "+txt[i]);
            if (string1[j].equals(string[i]))
            {
                //display.appendln("OK");
                j++;
                i++;
            }
            if (j == M)
            {
                a=true;
                //display.appendln("Found pattern "+
                              //"at index " + (i-j));
                j = pref[j-1];
            }
 
            // mismatch after j matches
            else if (i < N && string1[j].equals(string[i])==false)
            {
                // Do not match pref[0..pref[j-1]] characters,
                // they will match anyway
                if (j != 0){
                    j = pref[j-1];
               
                }
                else{
                    i = i+1;       
                }
            }
        }
        return a;
    }
 
    public static void LPSArray(String[] pat, int M, int pref[])
    {
        // length of the previous longest prefix suffix
        int l = 0;
        int i = 1;
        pref[0] = 0;  // pref[0] is always 0
 
        // the loop calculates pref[i] for i = 1 to M-1
        while (i < M)
        {
            if (pat[i].equals(pat[l]))
            {
                l++;
                pref[i] = l;
                i++;
            }
            else  
            {               
                if (l != 0)
                {
                    l = pref[l-1];
                }
                else  
                {
                    pref[i] = l;
                    i++;
                }
            }
        }
    }
    public static boolean NaiveSearch(String[] words1, String[] words2){
        boolean a = false;
           String[] string = words1;
           String[] string1 = words2;
    int  m = string1.length;
    int n = string.length;
    for(int i = 0;i<=n-m;i++)
    {
        int j=0;
        do
        {
            String partsa = string[i+j];
            String partsb = string1[j];

            if(!(partsa.equals(partsb))) 
                { 
            	j++;
                break;
                }
       j++;
       if(j==m)
        {  
        a=true;
        }}while(j<m);
        
    }
    //System.out.println("Naive is returning "+a);
    return a ;
    }
     public boolean boyer_moore(String txt1, String pat1)
    {
        boolean a = false;String s="",p="";
        char[] text = txt1.toCharArray();
        char[] pattern = pat1.toCharArray();
        int position = index(text, pattern);
        if (position != -1)
        {
        display.append("Pattern found at position : "+ position);
        a=true;
        }   
        return a;
    }

     public static int[] offsetTable(char[] pattern) 
{
        int[] table = new int[pattern.length];
        int lastPrefixPosition = pattern.length;
        for (int i = pattern.length - 1; i >= 0; --i) 
        {
            if (pref(pattern, i + 1)) 
                   lastPrefixPosition = i + 1;
              table[pattern.length - 1 - i] = lastPrefixPosition - i + pattern.length - 1;
        }
        for (int i = 0; i < pattern.length - 1; ++i) 
        {
              int sl = suff_l(pattern, i);
              table[sl] = pattern.length - 1 - i + sl;
        }
        return table;
    }
     public static boolean pref(char[] pattern, int p) 
    {
        for (int i = p, j = 0; i < pattern.length; ++i, ++j) 
            if (pattern[i] != pattern[j]) 
                  return false;
        return true;
    }
    /** function to returns the maximum length of the substring ends at p and is a suffix **/
    private static int suff_l(char[] pattern, int p) 
    {
        int l = 0;
        for (int i = p, j = pattern.length - 1; i >= 0 && pattern[i] == pattern[j]; --i, --j) 
               l += 1;
        return l;
    }
    /** Function to calculate index of pattern substring **/
    public int index(char[] text, char[] pattern) 
    {
        if (pattern.length == 0) 
            return 0;
        int charTable[] = charTable(pattern);
        int offsetTable[] = offsetTable(pattern);
        for (int i = pattern.length - 1, j; i < text.length;) 
        {
 for (j = pattern.length - 1; pattern[j] == text[i]; --i, --j) 
 if (j == 0)  return i;
 i += Math.max(offsetTable[pattern.length - 1 - j], charTable[text[i]]);
        }
        return -1;
      }
      /** Makes the jump table based on the mismatched character information **/
      public int[] charTable(char[] pattern) 
      {
        final int ALPHABET_SIZE = 256100;
        int[] table = new int[ALPHABET_SIZE];
        for (int i = 0; i < table.length; ++i) 
               table[i] = pattern.length;
        for (int i = 0; i < pattern.length - 1; ++i) 
               table[pattern[i]] = pattern.length - 1 - i;
        return table;
      }
      /** Makes the jump table based on the scan offset which mismatch occurs. **/
      
    /** function to check if needle[p:end] a prefix of pattern **/
    
    String str1;
    private String LCSS(String txt1, String pat1){ 
    String string = txt1;
    String string1 = pat1;
    String[] str1 = string.replace(".", "").replace(",", "").replace("?", "").replace("!","").split(" ");
    String[] str2 = string1.replace(".", "").replace(",", "").replace("?", "").replace("!","").split(" ");
    int l1 = str1.length;
    int l2 = str2.length;
    int[][] arr = new int[l1 + 1][l2 + 1];
    for (int i = 0; i <l1; i++)
    {
        int j=0;
        do
        {
            if(i==0 || j==0)
                arr[i][j]=0;
            else if (str1[i].equalsIgnoreCase(str2[j]))
                arr[i][j] = arr[i-1][j-1] + 1;
            else 
                arr[i][j] = Math.max(arr[i-1][j], arr[i][j-1]);
          j++;
        }while(j<l2);
    }
    int i = 0, j = 0;
    String sb = "";
    while (i < l1 && j < l2) 
    {
        if (str1[i].equalsIgnoreCase(str2[j])) 
        {
            sb+= str1[i]+" ";
            i++;
            j++;
        }
         else if (arr[i + 1][j] >= arr[i][j + 1])     i++;
         else   j++;
     }
    return sb.toString();
}
//The Following Function is used to break the content present in the given file into sentences.
    
private String[] breakFileIntoSentences(File file) 
{
ArrayList<String> sentenceList = null;
String[] brokenSentencesArray=null;
String line=null;
String text="";
String filePathString=file.getAbsolutePath();
try 
{
FileReader fileReader = new FileReader(filePathString);
BufferedReader bufferedReader = new BufferedReader(fileReader);
while((line = bufferedReader.readLine())!=null){
text+=line;
}
bufferedReader.close();
}
catch (FileNotFoundException e) 
{
e.printStackTrace();
}
catch (IOException e) 
{
e.printStackTrace();
}
brokenSentencesArray = text.split("(?<=[.!?])\\s*"); 
return brokenSentencesArray;
}

    public Frame1() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        display = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2000, 2000));
        getContentPane().setLayout(null);

        jButton1.setText("Import File From Dir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 60, 150, 30);

        jButton2.setText("Naive String Searching");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(20, 110, 150, 30);

        jButton3.setText("KMP");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(20, 150, 150, 30);

        jButton4.setText("LCSS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(20, 230, 150, 30);

        jButton5.setText("Boyer_Moore");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(20, 190, 150, 30);

        display.setColumns(20);
        display.setRows(5);
        jScrollPane1.setViewportView(display);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(210, 50, 430, 270);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Plagiarism Detection");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(220, 10, 240, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // This code is for importing the file from the directory
JFileChooser chooser = new JFileChooser(); // The File Chooser Pop Up Window.
chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
chooser.setAcceptAllFileFilterUsed(false);
  if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
  { 
      System.out.println("getCurrentDirectory(): " 
         +  chooser.getCurrentDirectory());
      System.out.println("getSelectedFile() : " 
         +  chooser.getSelectedFile());
  }
  else 
  {
      System.out.println("No Selection ");
  }  
File path = chooser.getSelectedFile();
File path_d=chooser.getCurrentDirectory();
s=path.getAbsolutePath();
name=path.getName();
d=path_d.getAbsolutePath();
display.append("\nSelected file:"+s);
display.append("\nSelected folder:"+d+"\n");                                    
    }//GEN-LAST:event_jButton1ActionPerformed
     String s;
     String d;
     String name;
     File path;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
long startTime = System.nanoTime();
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String folderpath = d;
final File folder = new File(folderpath);
display.append("\n"+"List of Files:");
listFilesForFolder(folder);
String testdoc1 = s;
String pat=read(testdoc1);
String[] pat1=split(pat);
boolean s=false;
int count=0;
display.append("\n"+"-------------------------------------------------------------PATTERN-------------------------------------------------------------\n");
print(pat1);
int patl = pat1.length;
File[] Files1=extract(folderpath,name);
String[] brokenSentences,plagiarizedSentences;
int flag=0;
File path1;
path1 = new File(testdoc1);
display.append("\n"+"testdoc"+testdoc1);
plagiarizedSentences = breakFileIntoSentences(path1);

// The following loop is where the comparison between each file is done.

for(int j=0;j<plagiarizedSentences.length;j++) //For each Plagiarised sentence.
{
display.append("\n\nSentence "+j+":"+plagiarizedSentences[j]+"\n");

// The following loop is where the contents of each file(input files) are read

for(int i=0;i<Files1.length;i++)
{
display.append("\n"+"File "+(i+1)+":");
brokenSentences = breakFileIntoSentences(Files1[i]);

for(int k=0;k<brokenSentences.length;k++)
{
String[] words1 = brokenSentences[k].replace(".", "").replace(",", "").replace("?", "").replace("!","").replace(";","").split(" ");
String[] words2 = plagiarizedSentences[j].replace(".", "").replace(",", "").replace("?", "").replace("!","").replace(";","").split(" ");
s = NaiveSearch(words1,words2);  //Function NaiveSearch is called.

if(s==true)
    {
        display.append("\n"+"**Copied from "+ Files1[i].getName()+ " sentence:"+k+" ");
        flag=1;    
    }
    else
    {
    	display.append("\n Sentence no match ");
    }
}}  
display.append("\n--------------------------------------------------------------------------------------------------------------------------------\n");
if(flag==0)
    {
    display.append("Not Copied");
    }
else
{
    count=count+1;
}
flag=0;
}
display.append("\n--------------------------------------------------------ANALYSIS SUMMARY---------------------------------------------------------\n");
display.append("\n"+"Total Sentences copied:"+count+"\n"); 
long endTime = System.nanoTime();
long duration = (endTime - startTime);
System.out.println("Naive duration"+duration);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
long startTime = System.nanoTime();
String folderpath = d;
final File folder = new File(folderpath);
display.append("\n"+"List of Files:");
listFilesForFolder(folder);
String testdoc1 = s;
String pat=read(testdoc1);
String[] pat1=split(pat);
boolean s=false;
int count=0;
display.append("\n-------------------------------------------------------------PATTERN-------------------------------------------------------------\n");
print(pat1);
File[] Files1=extract(folderpath,name);
String[] brokenSentences,plagiarizedSentences;
int flag=0;
File path1;
path1 = new File(testdoc1);
display.append("\n"+"testdoc"+testdoc1);
plagiarizedSentences = breakFileIntoSentences(path1);

// The following loop is where the comparison between each file is done.

for(int j=0;j<plagiarizedSentences.length;j++) // For each plagiarised sentence.
{
display.append("\n\nSentence "+j+":"+plagiarizedSentences[j]+"\n");

// The following loop is where the contents of each file(input files) are read 
for(int i=0;i<Files1.length;i++)
{
List<Float> fList = new ArrayList<Float>();
display.append("\n"+"File "+(i+1)+":");
brokenSentences = breakFileIntoSentences(Files1[i]); // Each file's content is stored in the String []

for(int k=0;k<(brokenSentences.length);k++)
{  
String[] words1 = brokenSentences[k].replace(".", "").replace(",", "").replace("?", "").replace("!","").replace(";","").split(" ");
String[] words2 = plagiarizedSentences[j].replace(".", "").replace(",", "").replace("?", "").replace("!","").replace(";","").split(" ");
s = KMP(words1,words2);// Function KMP is called.
if(s==true)
    {
        display.append("\n"+"**Copied from "+ Files1[i].getName()+ " sentence:"+k+" ");
        flag=1;    
    }
    else
    {
    	display.append("\n Sentence no match ");    	
    }
}
    }
display.append("\n--------------------------------------------------------------------------------------------------------------------------------\n");
if(flag==0)
    {
    display.append("Not Copied");
    }
else
{
    count=count+1;
}
}
display.append("\n--------------------------------------------------------ANALYSIS SUMMARY---------------------------------------------------------\n");
display.append("\n"+"Total Sentences copied:"+count+"\n"); 
display.append("\n****************************************************************************************************************************************************************\n");
long endTime = System.nanoTime();
long duration = (endTime - startTime);
System.out.println("KMP duration:"+duration);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
long startTime = System.nanoTime();
String folderpath = d;
final File folder = new File(folderpath);
display.append("\n"+"List of Files:");
listFilesForFolder(folder);
String testdoc1 = s;
display.append("\n"+"testdoc:"+testdoc1);
String pat=read(testdoc1);
String[] pat1=split(pat);
boolean s=false;
int count=0;
display.append("\n"+"-------------------------------------------------------------PATTERN-------------------------------------------------------------\n");
print(pat1);
File[] Files1=extract(folderpath,name);
String[] brokenSentences;
int flag=0;
File path1;
path1 = new File(testdoc1);
display.append("\n"+"testdoc"+testdoc1);
String[] plagarizedSentences = breakFileIntoSentences(path1);

// The following loop is where the comparison between each file is done.
for(int j=0;j<plagarizedSentences.length;j++)
{
    display.append("\n\nSentence "+j+":"+plagarizedSentences[j]+"\n");
    
    // The following loop is where the contents of each file(input files) are read 
for(int i=0;i<Files1.length;i++)
{

display.append("\n"+"File "+(i+1)+":");
brokenSentences = breakFileIntoSentences(Files1[i]);
for(int k=0;k<brokenSentences.length;k++) // Each file's content is stored in the String []
{
String wr1=brokenSentences[k].toString();
String wr2=plagarizedSentences[j].toString();
s = boyer_moore(wr1,wr2); // The boyer_moore function is called along with the values
if(s==true)
{
    display.append("\n"+"**Copied from "+ Files1[i].getName()+ " sentence:"+k+" ");
    flag=1;    
}
else
{
    display.append("\n Sentence no match ");
}
}}  
display.append("\n--------------------------------------------------------------------------------------------------------------------------------\n");
if(flag==0)
{
display.append("Not Copied");
}
else
{
count=count+1;
}
flag=0;
}
display.append("\n--------------------------------------------------------ANALYSIS SUMMARY---------------------------------------------------------\n");
display.append("\n"+"Total Sentences copied:"+count+"\n"); 
long endTime = System.nanoTime();
long duration = (endTime - startTime);
System.out.println("Boyer-Moore duration"+duration);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
long startTime = System.nanoTime();
String folderpath = d;
final File folder = new File(folderpath);
display.append("\n"+"List of Files:");
listFilesForFolder(folder);
String testdoc1 = s;
display.append("\n"+"testdoc:"+testdoc1);
String pat=read(testdoc1);
String[] pat1=split(pat);
boolean s=false;
display.append("\n"+"-------------------------------------------------------------PATTERN-------------------------------------------------------------\n");
print(pat1);
int patl = pat1.length;
File[] Files1=extract(folderpath,name);
int l=Files1.length;
String matched="";
float denominator;
float numerator=0;
float percentage;
String[] arr;
display.append("\n"+"------------------------------------------------------PLAGIARIARISM CHECK---------------------------------------------------\n");

//Loop for iterating between the paragraphs in the plagiarised text
for(int b=0;b<patl;b++)
{
    display.append("\n"+"Sentence"+b+": "+pat1[b]+"\n\n"); 
    int flag=0;
    float[] max_array={};
    float[] fArray= {};
    max_array = new float[Files1.length];
    
   //Loop for iterating between the original text files  
for(int i=0;i<Files1.length;i++)
{
    	List<Float> fList = new ArrayList<Float>();        //display.appendln("File:" + Files1[i].getName());
        String path=Files1[i].getAbsolutePath();
        String text = read(path);
        String[] txt1=split(text);
        int length = txt1.length; 
    //Loop for iterating the paragraphs in the original selected file in the upper loop
for(int a=0;a<length;a++)
{   
matched = LCSS(txt1[a],pat1[b]);
 arr = matched.split(" ");
denominator = pat1.length;
if(arr[0]=="")
{
numerator=0;
percentage = (numerator/denominator)*100;
fList.add(percentage);
}
else
{
numerator = arr.length;
percentage = (numerator/denominator)*100;
fList.add(percentage);
}
if(percentage>35){
display.append("\n\nSentence "+(a+1)+" of file "+(i+1)+" is: "+txt1[a]);
if(matched!=""){
display.append("\n"+"Longest Common Subsequence of sentence "+(b+1)+": "+ matched+"\t");
}
}}
}}
long endTime = System.nanoTime();
long duration = (endTime - startTime);
System.out.println("LCSS duration"+duration);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Frame f =new Frame1();
                f.setSize(700,400);
                        f.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea display;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
