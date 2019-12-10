package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import main.Constraint;
import main.GrafcetOut;
import main.Infos;
import main.LCtoLAC;

public class LCtoLACTests extends LCtoLAC {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void isMergeableTest() {
		Infos a = new Infos("1", "2", "up", "G"); // information avec une
													// liaison qui commence avec
													// Maj
		if (!isMergeable(a)) {
			fail("liaison commence avec Majuscule");
		}
		;
		a = new Infos("1", "2", "up", "g"); // information avec une liaison qui
											// commence avec Maj
		if (isMergeable(a)) {
			fail("liaison commence avec Miniscule");
		}
		;
	}

	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void ProcessTest() { // test si l'output de la
													// fonction process est
													// correct

//		File file = new File("./");
//		for (String fileNames : file.list())
//			System.out.println(fileNames);

		final File expected = new File("./src/test/expected.txt");
		File tempFile;
		try {
			tempFile = folder.newFile("tempFile.txt");
			List<Infos> lines = process("input.txt");
			if (lines == null) {
				fail("process failed, couldn't acess the file");
			}
			String header1 = "@startuml";
			String header2 = "left to right direction";
			String footer = "@enduml";
				FileUtils.writeStringToFile(tempFile,
						header1 + System.lineSeparator(),"UTF-8", true);
				FileUtils.writeStringToFile(tempFile,
						header2 + System.lineSeparator(),"UTF-8", true);
				for (int i = 0; i < lines.size(); i++) {
					if (lines.get(i).isStart()) {
						String a = "(start) --> ";
						a += lines.get(i).getInf1().toString() == null ? lines
								.get(i).getNode1().toString() : lines.get(i)
								.getInf1().toString();
						FileUtils.writeStringToFile(tempFile,
								a + System.lineSeparator(),"UTF-8", true);
					}
				}
				for (int i = 0; i < lines.size(); i++) {
					String str = lines.get(i).toString();
					FileUtils.writeStringToFile(tempFile,
							str + System.lineSeparator(),"UTF-8", true);
				}
				FileUtils.writeStringToFile(tempFile,
						footer + System.lineSeparator(),"UTF-8", true);
		
			Assert.assertEquals("the LAC model created is incorrect",FileUtils.readLines(expected,"UTF-8"),
					FileUtils.readLines(tempFile,"UTF-8"));


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			fail("process failed, the file coudn't be found or it is empty");		
//			e1.printStackTrace();
		}
			}

	@Test
	public void LACtoDCTest() { // test si l'output de la
													// fonction LACtoDC est
													// correct

		// File file = new File("./"); for(String fileNames : file.list())
		// System.out.println(fileNames);
		//

		try {

			final File expected = new File("./src/test/expectedDC.txt");
			final File tempFile = folder.newFile("tempFile.txt");
			List<Infos> line = process("input.txt");

			List<Constraint> contraintes = parseGC("src/constraints.gc");
			
			List<Infos> lines = LACtoDC(line, contraintes);

			String header1 = "@startuml";
			String header2 = "left to right direction";
			String footer = "@enduml";
			FileUtils.writeStringToFile(tempFile,
					header1 + System.lineSeparator(),"UTF-8", true);
			FileUtils.writeStringToFile(tempFile,
					header2 + System.lineSeparator(),"UTF-8", true);
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).isStart()) {
					String a = "(start) --> ";
					a += lines.get(i).getInf1().toString() == null ? lines
							.get(i).getNode1().toString() : lines.get(i)
							.getInf1().toString();
					FileUtils.writeStringToFile(tempFile,
							a + System.lineSeparator(),"UTF-8", true);
				}
			}
			for (int i = 0; i < lines.size(); i++) {
				String str = lines.get(i).toString();
				FileUtils.writeStringToFile(tempFile,
						str + System.lineSeparator(),"UTF-8", true);
			}
			FileUtils.writeStringToFile(tempFile,
					footer + System.lineSeparator(),"UTF-8", true);

			
			Assert.assertEquals("the DC model created is incorrect",FileUtils.readLines(expected,"UTF-8"),
					FileUtils.readLines(tempFile,"UTF-8"));

		} catch (IOException e) {
			fail("LAC to DC failed, the file coudn't be found or it is empty");
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}

	
	@Test
	public void DCtoGrafcetTest() { // test si l'output de la
													// fonction LDCtoGrafcet est
													// correct

		// File file = new File("./"); for(String fileNames : file.list())
		// System.out.println(fileNames);
		//

		try {

			final File expected = new File("./src/test/expectedGrafcet.json");
			final File tempFile = folder.newFile("tempFile.json");
			List<Infos> line = process("input.txt");

			List<Constraint> contraintes = parseGC("src/constraints.gc");
			
			List<Infos> lin = LACtoDC(line, contraintes);
			
			GrafcetOut lines = DCtoGrafcet(lin);

			
			String header1 = "{\"class\": \"go.GraphLinksModel\",";
			String header2 = "\"nodeDataArray\": [";
			String header3 = "\"linkDataArray\": [";
//			try {
			FileUtils.writeStringToFile(tempFile,header1 + System.lineSeparator(),"UTF-8", true);
			FileUtils.writeStringToFile(tempFile,header2 + System.lineSeparator(),"UTF-8", true);
				for (int i = 0; i < lines.grafs.size() - 1; i++) {
					String str = lines.grafs.get(i).toString();
					FileUtils.writeStringToFile(tempFile,str + "," + System.lineSeparator(),"UTF-8", true);
				}

				FileUtils.writeStringToFile(tempFile,lines.grafs.get(lines.grafs.size() - 1).toString()
						+ System.lineSeparator(),"UTF-8", true);
				FileUtils.writeStringToFile(tempFile,"]," + System.lineSeparator(),"UTF-8", true);
				FileUtils.writeStringToFile(tempFile,header3 + System.lineSeparator(),"UTF-8", true);
				for (int i = 0; i < lines.links.size() - 1; i++) {
					String str = lines.links.get(i).toString();
					FileUtils.writeStringToFile(tempFile,str + ',' + System.lineSeparator(),"UTF-8", true);
				}
				FileUtils.writeStringToFile(tempFile,lines.links.get(lines.links.size() - 1).toString()
						+ System.lineSeparator(),"UTF-8", true);
				FileUtils.writeStringToFile(tempFile,"]}" + System.lineSeparator(),"UTF-8", true);

			
			
			Assert.assertEquals("the Grafcet model created is incorrect",FileUtils.readLines(expected,"UTF-8"),
					FileUtils.readLines(tempFile,"UTF-8"));

		} catch (IOException e) {
			fail("DC to Grafcet failed, the file coudn't be found or it is empty");
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}

	
	
	
}
