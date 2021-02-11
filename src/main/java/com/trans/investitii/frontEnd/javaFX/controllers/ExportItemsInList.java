package com.trans.investitii.frontEnd.javaFX.controllers;

import com.trans.investitii.frontEnd.javaFX.controllers.admin.ControllerStageAdminFz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ExportItemsInList {

    public static void exportFzList () throws IOException {

        BufferedReader fz1 =new BufferedReader( new FileReader("C:\\Investitii\\resurse\\fz" ) );
        int lineCount = (int) Files.lines( Paths.get( "C:\\Investitii\\resurse\\fz" ) ).count();
        for (int i = 0; i < lineCount; ++i) {
            System.out.println(fz1.readLine());




        }
            }
        }

