package com.betha.pessoal.scripts.padrao
import com.betha.pessoal.scripts.utils;
#parse("File Header.java")
#set($capitalizedFilename = $NAME.substring(0,1).toUpperCase() + $NAME.substring(1))
#set($lowCaseName = $NAME.toLowerCase())
class $capitalizedFilename {
    Object data = utils.getFile("$lowCaseName");

    

    

}

