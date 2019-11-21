package com.betha.pessoal.scripts.saida

import static com.betha.pessoal.scripts.padrao.bfcScript.*
import groovy.json.JsonOutput


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


static getParametrosFuncao(String line) {
    String args = (line =~ /.*(\(.*\)).*/)[0][1]

    args = args.substring(1, args.length() - 1)

    def list = args.split(',')
    def listaDeArgumentos = []

    list.eachWithIndex { entry, index ->
        def nome = entry.split()[entry.split().size() - 1]
        listaDeArgumentos.push(["posicao": index, "nome": nome, "descricao": nome])
    }
    return JsonOutput.toJson(listaDeArgumentos)
}

static ler(String folder, String file) {
    BufferedReader reader;
    String fileContent = ''
    boolean iniciarFinalizarScriptFuncao
    String nomeFuncao = file.replace('.groovy', '')
    String output = '"tipo": "CALCULO_FOLHA" , "nome" : "' + nomeFuncao + '" , "descricao" : "' + nomeFuncao + '",'
    try {
        reader = new BufferedReader(new FileReader(folder + "\\" + file));
        String line = reader.readLine();



        while (line != null) {

            line = line.replaceAll('"',"'")

            if (line ==~ /.*static.*\(\s*[a-z|A-Z].*\s*\).*/) {
                output += '"argumentos" : ' + getParametrosFuncao(line) + ','
            }

            if (line ==~ /\s*\/\*\*\s*Dentro\s*deste\s*bloco.*/) {
                iniciarFinalizarScriptFuncao = true
            }

            if (line ==~ /\s*\/\*\*\s*FINAL\s*.*/) {
                iniciarFinalizarScriptFuncao = false
            }

            if (iniciarFinalizarScriptFuncao) {
                if (!(!line || line ==~ /\s*package\s*.*/ || line ==~ /\s*import\s*.*/ || line ==~ /\s*imprimir\s*.*/ || line ==~ /\s*\\/\*\*\**.*/ || line ==~ /\s*def\s*evento\s*\=\s*\[.*/)) {

                    if (line.trim().length() > 0) {
                        // System.out.println(line);
                        if (line.length() > 8) {
                            if (line.substring(0, 8).trim() == '') {
                                line = line.substring(8, line.length())
                            }
                        }
                        fileContent += line + "\\n"
                    }
                }
            }
            // read next line
            line = reader.readLine();
        }


        // iniciarScriptFuncao = false
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    if (file != 'funcoes.groovy') {
        if (fileContent.trim() == '') {
            System.out.println('Sem fórmula')
            suspender "Sem fórmula"
        }

        if (!(output ==~ /.*argumentos\"\s*\:\s*\[.*/)) {
            output = '{' + output + '"conteudo" : "' + fileContent.replace('\"', '\\"') + '" , "argumentos" : [] }'
        } else {
            output = '{' + output + '"conteudo" : "' + fileContent.replace('\"', '\\"') + '"}'
        }

        String outputdir = System.getProperty("user.home") + "\\betha\\formulas-funcoes\\funcoes"
        //Cria a pasta
        new File(outputdir).mkdirs();

        escrever(output, outputdir + '\\' + nomeFuncao + '.json')
    }

}

public static void escrever(String fileContent, String outputFile) throws IOException {
    FileWriter fileWriter = new FileWriter(outputFile);
    fileWriter.write(fileContent);
    fileWriter.close();
}


String path = "C:\\dev\\folha\\src\\main\\java\\com\\betha\\pessoal\\scripts\\funcoes\\"
final File folder = new File(path);
listFilesForFolder(folder);

