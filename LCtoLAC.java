import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SpringLayout.Constraints;

public class LCtoLAC {
	private static String up = "<&arrow-top>";
	// private static String down = "<&arrow-bottom>";

	private static BufferedReader bufRead;

	/**
	 * check if the two nodes are mergeable or not.
	 * 
	 * @param infos
	 *            type Infos will be used to define if the two nodes are
	 *            mergeable
	 * @return if the $liason starts with an upperCase character it returns true
	 */
	private static boolean isMergeable(Infos infos) {
		boolean out = false;
		if (Character.isUpperCase(infos.getLiaison().charAt(0))) {
			out = true;
		}

		return out;
	}

	/*
	 * * merge two node given in an Infos type element.
	 * 
	 * @param infos type Infos will be used to define the two nodes to merge
	 * 
	 * @return out of type InfosLAC containing the new node, only the node
	 */
	private static Infos merge2(Infos info, List<Infos> infos) {
		Infos infoOut = new Infos();
		if (info.isStart()) {
			infoOut.setStart(true);
		}
		InfosLAC out = new InfosLAC();
		if (info.getDirection().equals(up)) {
			if (info.getInf1() == null) {
				if (info.getInf2() == null) {
					out.setOrd(info.getLiaison());
					infoOut = find2ndNode(info.getNode2(), infos).get(0);
					infoOut.setInf1(out);
				} else {
					infoOut = find2ndNode(info.getInf2(), infos).get(0);
					out.setOrd(info.getLiaison());
					out.setInh(info.getInf2().getInh());
					infoOut.setInf1(out);
				}
			} else {
				if (info.getInf2() == null) {
					System.out.println(info.getNode2());
					infoOut = find2ndNode(info.getNode2(), infos).get(0);
					out.setOrd(info.getLiaison());
					out.setInh(info.getInf1().getInh());
					infoOut.setInf1(out);
				}
			}
		} else {
			if (info.getInf1() == null) {
				if (info.getInf2() == null) {
					out.setInh(info.getLiaison());
					infoOut = find2ndNode(info.getNode2(), infos).get(0);
					infoOut.setInf1(out);
				} else {
					infoOut = find2ndNode(info.getInf2(), infos).get(0);
					out.setInh(info.getLiaison());
					out.setOrd(info.getInf2().getOrd());
					infoOut.setInf1(out);
				}
			} else {
				if (info.getInf2() == null) {
					infoOut = find2ndNode(info.getNode2(), infos).get(0);
					out.setInh(info.getLiaison());
					out.setOrd(info.getInf1().getOrd());
					infoOut.setInf1(out);
				}
			}
		}

		return infoOut;
	}

	/**
	 * merge two node given in an Infos type element.
	 * 
	 * @param infos
	 *            type Infos will be used to define the two nodes to merge
	 * @return out of type InfosLAC containing the new node, only the node
	 */
	/*
	 * private static InfosLAC merge(Infos info) { InfosLAC out = new
	 * InfosLAC(null, null); if (info.getDirection().equals(up)) {
	 * out.setOrd(info.getLiaison()); } else { out.setInh(info.getLiaison()); }
	 * 
	 * return out; }
	 */

	/*
	 * while the list isn't in its final state which means there are more nodes
	 * to merge we keep merging
	 * 
	 * @param infos type List<Infos> will be used as a source
	 * 
	 * @return out of type List<Infos> containing the new Infos list, the final
	 * one.
	 */

	private static List<Infos> mergeRecur(List<Infos> infos) {

		List<Infos> out = mergeAll(infos);
		if (out.size() != 0) {
			out = mergeAll(out);
		} else {
			return infos;
		}
		return out;
	}

	/*
	 * if some nodes are not linked the function search its successor and add it
	 * to the list
	 * 
	 * @param infos type List<Infos> will be used as a source
	 * 
	 * @return out of type List<Infos> containing the new Infos list with no
	 * unlinked nodes
	 */
	private static List<Infos> completeList(List<Infos> infos) {
		String a = null;
		for (int j = 0; j < infos.size(); j++) {
			a = infos.get(j).getNode2().toString();
			boolean k = true;
			for (int i = 0; i < infos.size(); i++) {
				if (infos.get(i).getNode1().equals(a)) {
					k = false;

				}

			}
			if (k) {
				infos.get(j).setInf2(infos.get(j + 1).getInf1());
			}
		}

		return infos;

	}

	/*
	 * this function is used to find the starting node.
	 * 
	 * @param infos type List<Infos> will be used as a source.
	 * 
	 * @param name type String is the file name, which contains the first
	 * schema.
	 * 
	 * @return void, it only changes the boolean parameter start in the Infos
	 * object to indicate the start.
	 */
	private static void findStart(List<Infos> lines, String name) {
		String line = null;
		try {
			FileReader input = new FileReader(name);
			bufRead = new BufferedReader(input);
			while ((line = bufRead.readLine()) != null) {
				// Sys)tem.out.println(line);
				if (line.contains(">")) {
					if (line.contains("(start)")) {
						String[] content = line.split("-->");
						deleteSpace(content[0]);
						deleteSpace(content[1]);

						// System.out.println("dazgdhazjbfazhk");
						for (int i = 0; i < lines.size(); i++) {
							if (deleteSpace(content[1]).equals(
									lines.get(i).getNode1().toString())) {
								lines.get(i).setStart(true);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * this is actually the main method it reads from the given list, it tests
	 * every 2 nodes, and if they are mergeable it merges them.
	 * 
	 * @param infos type List<Infos> will be used as a source.
	 * 
	 * @return void, it only changes the boolean parameter start in the Infos
	 * object to indicate the start.
	 */
	private static List<Infos> mergeAll(List<Infos> infos) {
		List<Infos> out = new ArrayList<Infos>();
		int i = 0;
		while (i < infos.size()) {
			Infos tmp = infos.get(i);
			if (isMergeable(tmp)) {
				Infos lac = merge2(tmp, infos);
				if (tmp.isStart()) {
					lac.setStart(true);
				}
				int j = 0;
				Infos a = infos.get(j);
				while (j < infos.size()) {
					if (a.getNode1() == lac.getNode2()) {
						infos.remove(j);
						j = 0;
					} else if (a.getNode2() == lac.getNode1()) {
						infos.remove(j);
						j = 0;
					} else {
					}
					j++;
				}

				out.add(lac);
			} else {
				if (tmp.getInf1() == null)
					out.add(tmp);
			}
			i++;
		}
		return out;
	}

	/**
	 * search for the next node, this method is going to be used to find the
	 * next node while merging.
	 * 
	 * @param node1
	 *            the name of the first node
	 * @return node2 the next node
	 */
	private static List<Infos> find2ndNode(Object node1, List<Infos> infos) {
		List<Infos> out = new ArrayList<Infos>();
		for (int i = 0; i < infos.size(); i++) {
			if (infos.get(i).getNode1().equals(node1)) {
				out.add(infos.get(i));

			} else if (infos.get(i).getInf1() == node1) {
				out.add(infos.get(i));
			}
		}
		return out;

	}

	/*
	 * search for the previous node, this method is going to be used to find the
	 * previous node while merging.
	 * 
	 * @param node1 the name of the 2nd node
	 * 
	 * @return node1 the previous node
	 */
	/*
	 * private static List<Infos> find1stNode(Object node2, List<Infos> infos) {
	 * List<Infos> out = new ArrayList<Infos>(); for (int i = 0; i <
	 * infos.size(); i++) { if (infos.get(i).getNode2().equals(node2)) {
	 * out.add(infos.get(i)); } }
	 * 
	 * return out; }
	 */

	/**
	 * check if the line contains needed infos, and by that I mean 2 nodes, the
	 * link that connects them, and the informations on the link.
	 * 
	 * @param line
	 *            the line to check
	 * @return true if it contains nodes, false otherwise
	 */
	private static boolean hasInfos(String line) {
		boolean test = false;
		String line1 = deleteSpace(line);
		if (line1.startsWith("(") && line1.contains(":")) {
			test = true;
		}
		return test;
	}

	/**
	 * read lines from the file given in input.
	 * 
	 * @param name
	 *            the file name
	 * @return ArrayList<String> containing all the lines
	 */

	private static List<String> readLines(String name) {
		List<String> lines = new ArrayList<>();
		String line = null;
		try {
			FileReader input = new FileReader(name);
			bufRead = new BufferedReader(input);
			while ((line = bufRead.readLine()) != null) {
				// Sys)tem.out.println(line);
				if (hasInfos(line)) {
					lines.add(line);
					// System.out.println(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lines;
	}

	/**
	 * delete the space in the beginning of a line
	 * 
	 * @param line
	 *            the line as a string
	 * @return String without space
	 */
	private static String deleteSpace(String line) {
		char[] a = line.toCharArray();
		String s;
		if (line.startsWith(" ")) {
			// System.out.println(11);
			s = "";
			for (int i = 1; i < line.length(); i++) {
				s += a[i];
			}
			return s;
		}
		return line;
	}

	/**
	 * get a line from a char to another
	 * 
	 * @param from
	 *            starting char
	 * @param to
	 *            the ending char
	 * @param line
	 *            the line to extract from
	 * @return String from $from to $to
	 */
	private static String getLineFromTo(String from, String to, String line) {
		char[] elts = line.toCharArray();

		String out;
		int i = 0;
		while (i < line.length()) {
			if (String.valueOf(elts[i]).equals(from)) {
				// System.out.println("1"+String.valueOf(elts[i]).equals(from));
				out = String.valueOf(elts[i]);
				if (to != (null)) {
					while (!String.valueOf(elts[i]).equals(to)) {
						// System.out.println("2 "+i);
						i++;
						out += elts[i];

					}
				} else {
					while (i < line.length() - 1) {
						// System.out.println("2 "+i);
						i++;
						out += elts[i];

					}
				}
				i = line.length();
				// System.out.println(new String(elts));
				return new String(out);
			} else {
				i++;
			}

		}
		return null;
	}

	/**
	 * read a line and extract its informations.
	 * 
	 * @param line
	 *            a line from the file
	 * @return Infos containing all the necessary informations
	 */

	private static Infos getLineInfos(String line) {
		Infos infos = null;
		String[] content = line.split(":");
		// System.out.println(content[0]);
		String[] nodes = content[0].split("-->");
		nodes[0] = getLineFromTo("(", ")", nodes[0]);
		// System.out.println(nodes[1]);
		nodes[1] = getLineFromTo("(", ")", nodes[1]);
		String[] direcAndText = new String[2];
		content[1] = deleteSpace(content[1]);
		direcAndText[0] = getLineFromTo("<", ">", content[1]);
		direcAndText[1] = getLineFromTo(" ", null, content[1]);
		infos = new Infos(deleteSpace(nodes[0]), deleteSpace(nodes[1]),
				deleteSpace(direcAndText[0]), deleteSpace(direcAndText[1]));
		return infos;

	}

	/**
	 * transform the lines to Infos array.
	 * 
	 * @param lines
	 *            a string array of lines
	 * @return List<Infos> containing all the infos as Infos format
	 */
	private static List<Infos> extractInfos(List<String> lines) {
		Infos info = null;
		List<Infos> infos = new ArrayList<Infos>();
		String line = null;
		for (int i = 0; i < lines.size(); i++) {
			line = lines.get(i);
			info = getLineInfos(line);
			infos.add(info);
		}

		return infos;
	}

	/**
	 * given a list of Infos it uses them to write into a file named
	 * "output.text" in the right format.
	 * 
	 * @param lines
	 *            a list of Infos.
	 * @return void
	 */
	private static void writeToFile(List<Infos> lines) {
		FileWriter writer;
		String header1 = "@startuml";
		String header2 = "left to right direction";
		String footer = "@enduml";
		try {
			writer = new FileWriter("src/output.txt");
			writer.write(header1 + System.lineSeparator());
			writer.write(header2 + System.lineSeparator());
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).isStart()) {
					System.out.println(1532496);
					String a = "(start) --> ";
					a += lines.get(i).getInf1().toString() == null ? lines
							.get(i).getNode1().toString() : lines.get(i)
							.getInf1().toString();
					System.out.println(a);
					writer.write(a + System.lineSeparator());
				}
				String str = lines.get(i).toString();
				writer.write(str + System.lineSeparator());
			}
			writer.write(footer + System.lineSeparator());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * given the file name, it transforms the file into an LAC model, which can
	 * be transformed to a graph using plantUML.
	 * 
	 * @param inputFile name of the inputFile
	 * 
	 * @return void
	 */

	private static List<Infos> process(String inputFile) {

		List<String> lines = readLines("src/" + inputFile);
		List<Infos> infos = extractInfos(lines);
		findStart(infos, "src/" + inputFile);

		/*
		 * for (int i = 0; i < infos.size(); i++) {
		 * System.out.println(infos.get(i)); //
		 * System.out.println(isMergeable(infos.get(i)));
		 * 
		 * }
		 */

		List<Infos> xy = mergeRecur(infos);

		completeList(xy);

		writeToFile(xy);

		return xy;
	}

	private static List<Constraint> parseGC(String name) {
		List<Constraint> a = new ArrayList<Constraint>();
		
		String line = null;

		try {
			FileReader input = new FileReader(name);
			bufRead = new BufferedReader(input);
			while ((line = bufRead.readLine()) != null) {
				Constraint constraint = new Constraint();
				String[] s1 =null;
				String[] s2 =null;
				s1=line.split(" Then ");
				s2 = s1[0].split("If ");
				constraint.setContrainte(s2[1]);
				constraint.setOrdInh(s1[1]);
				a.add(constraint);
				//System.out.println(constraint);				
			}
			return a;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}

	private static void LACtoDC(List<Infos> liste, List<Constraint> contraintes) {
		for (int i = 0; i < liste.size(); i++) {
			for (Constraint c : contraintes) {
				if (liste.get(i).getInf1() != null) {
					System.out.println(("Ord: "+liste.get(i).getInf1().getOrd() + "    121 " +c.getOrdInh()));
					//System.out.println(("Ord: "+liste.get(i).getInf1().getOrd() + "    121 " +c.getOrdInh()));
					System.out.println(10);
					if (("Inh: "+liste.get(i).getInf1().getInh()).equals(c.getOrdInh())) {
						System.out.println(11);
						System.out.println(11);
						liste.get(i)
								.getInf1()
								.setInh(liste.get(i).getInf1().getInh()
										+ "\\n If : " + c.getContrainte());
					}
					if (("Ord: "+liste.get(i).getInf1().getOrd()).equals(c.getOrdInh())) {
						System.out.println(("Ord: "+liste.get(i).getInf1().getOrd() + "    121" +c.getOrdInh()));
						System.out.println(13);
						liste.get(i)
								.getInf1()
								.setOrd(liste.get(i).getInf1().getOrd()
										+ "\\n If : " + c.getContrainte().replace("(","<&chevron-left>").replace(")","<&chevron-right>").replace("up", "<&arrow-top>").replace("or", "<&chevron-bottom>").replace("and", "<&chevron-top>").replace("->", "<&arrow-right>")+"");
					} 
				}
			}
		}

		writeToFile2(liste);
	}

	private static void writeToFile2(List<Infos> lines) {
		FileWriter writer;
		String header1 = "@startuml";
		String header2 = "left to right direction";
		String footer = "@enduml";
		try {
			writer = new FileWriter("src/outputDC.txt");
			writer.write(header1 + System.lineSeparator());
			writer.write(header2 + System.lineSeparator());
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).isStart()) {
					System.out.println(1532496);
					String a = "(start) --> ";
					a += lines.get(i).getInf1().toString() == null ? lines
							.get(i).getNode1().toString() : lines.get(i)
							.getInf1().toString();
					System.out.println(a);
					writer.write(a + System.lineSeparator());
				}
				String str = lines.get(i).toString();
				writer.write(str + System.lineSeparator());
			}
			writer.write(footer + System.lineSeparator());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		/*
		 * File file = new File("/"); for(String fileNames : file.list())
		 * System.out.println(fileNames);
		 */
		List<Constraint> contraintes = parseGC("src/constraints.gc");
	
		  //for(Constraint a : contraintes){ System.out.println("a " +a);}
		 
		List<Infos> liste = process("input.txt");
		/*
		 * for(Infos i : liste){ System.out.println("i " + i); }
		 */
		LACtoDC(liste, contraintes);

	}
}
