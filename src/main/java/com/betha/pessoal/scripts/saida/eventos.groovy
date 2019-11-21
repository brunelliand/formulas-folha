package com.betha.pessoal.scripts.saida




public void listFilesForFolder(final File folder) {

    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {

            System.out.println(fileEntry.getName());
            ler(folder as String, fileEntry.getName())
        }
    }
}

static ler(String folder, String file) {
    BufferedReader reader;
    String fileContent = ''
    try {
        reader = new BufferedReader(new FileReader(folder + "\\" + file));
        String line = reader.readLine();

        while (line != null) {

            //line = line.replaceAll('"',"'")

            if (!(!line || line ==~ /\s*package\s*.*/ || line ==~ /\s*import\s*.*/ || line ==~ /\s*imprimir\s*.*/ || line ==~ /\s*\\/\*\**.*/ || line ==~ /\s*def\s*evento\s*\=\s*\[.*/)) {

                if (line.trim().length() > 0) {
                    //System.out.println(line);
                    fileContent += line + "\n"
                    fileContent = fileContent
                }
            }
            // read next line
            line = reader.readLine();

        }

        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    String outputdir = System.getProperty("user.home")+"\\betha\\formulas-funcoes\\eventos"

    //Cria a pasta
    new File(outputdir).mkdirs();

    escrever(fileContent.replace('\"','\\"'), outputdir + "\\" + file)

}

public static void escrever(String fileContent, String outputFile) throws IOException {
    FileWriter fileWriter = new FileWriter(outputFile);
    fileWriter.write(fileContent);
    fileWriter.close();
}


String path = "C:\\dev\\folha\\src\\main\\java\\com\\betha\\pessoal\\scripts\\eventos\\"
final File folder = new File(path);
listFilesForFolder(folder);

