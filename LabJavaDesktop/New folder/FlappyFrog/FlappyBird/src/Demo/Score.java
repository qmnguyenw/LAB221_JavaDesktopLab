package Demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Score {
	public int Best(int diem) throws IOException {
		int value=0;
		int i;
		FileInputStream fin;
		FileOutputStream fout;
		try	{
			fin=new FileInputStream("test.txt");
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found !");
			return 0;
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("xxxxx!");
			return 0;
		}
		do {
			i=fin.read();
			if(i!=-1) 
				value=value*10+((char)i-'0');
		} while(i!=-1);
		
		if(diem>value){
			try	{
				fout=new FileOutputStream("test.txt");
			}catch(FileNotFoundException e)	{
				System.out.println("File Not Found !");
				return 0;
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("xxxxx!");
				return 0;
			}
			
			String s=String.valueOf(diem);
			for(int j=0;j<s.length();j++) fout.write(s.charAt(j)); 
			value=diem;
			fout.close();
		}
		fin.close();
		return value;
	}
}
