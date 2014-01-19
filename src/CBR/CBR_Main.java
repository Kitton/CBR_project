package CBR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CBR_Main {
	public static void main(String[] args) throws IOException {
		int n = DataReader.readData("./data/pendigits_100.tes").size();
		System.out.println(n + " cases were read");
	}
}
