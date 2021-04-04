package com.trans.investitii.backEnd.DBase;

import javafx.scene.control.TextField;

import java.time.LocalDate;

public class Investitii {
    public   static String USER= "root";
    public   static String PASSWORD= "root";
    public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS invDB";
    public static final String USE_DATABASE = "USE invDB";
    public static String URL0 = String.format( "jdbc:mysql://localhost:3306/%s?useLegacyDateTimeCode=false&serverTimezone=GMT","sys" );
    public static String URL = String.format( "jdbc:mysql://localhost:3306/%s?useLegacyDateTimeCode=false&serverTimezone=GMT","invDB" );
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS invTBL (nrCrt INT(5) AUTO_INCREMENT PRIMARY KEY, furnizor CHAR(100) not null, nrFactura char(20) , dataFacturii DATE, dataContabilizarii DATE, valoare varCHAR(20), nrPIF CHAR (50), dataPIF DATE, descriereFactura varChar(210), valInitiala varCHAR(20), tva varCHAR(20), valTot varCHAR(20), contract CHAR(25), contInv CHAR(20), contFz CHAR(20), nrProiect CHAR(20), deviz CHAR(20), org CHAR(10), respProiect CHAR(50))";
    public static final String CREATE_TABLE_BUGET_ORG = "CREATE TABLE IF NOT EXISTS bugetORG (nrCrt INT(5) AUTO_INCREMENT PRIMARY KEY, org CHAR(10) NOT NULL,denumireOrg CHAR(70), anulBugetar CHAR(4), valInitiala varChar(20), valRectificata CHAR(20), valFinala CHAR(20))";
    public static final String CREATE_TABLE_BUGET_PROJ = "CREATE TABLE IF NOT EXISTS bugetPROJ (nrCrt INT(5) AUTO_INCREMENT PRIMARY KEY, nrProiect CHAR(20), denProiect CHAR(200), startProiect DATE, valInitiala VARCHAR(20), valRectificare VARCHAR(20) NOT NULL, valFinala VARCHAR(20))";
    public static final String CREATE_TABLE_BUGET_CONTRACT = "CREATE TABLE IF NOT EXISTS bugetCONTRACT (nrCrt INT(5) AUTO_INCREMENT PRIMARY KEY, nrContract CHAR(25),dataContract DATE, furnizor CHAR(100), CUIfurnizor CHAR(14), adresa CHAR (100),valInitiala VARCHAR(20), valRectificare VARCHAR(20) NOT NULL, valFinala VARCHAR(20) )";
    public static final String USE_TABLE = "USE invTBL";

//    public static final String UserWeb = "imobiliz_sorin";
//    public static final String PASSWORDWEB = "PoiuLkjh123";
//    public static final String URLWEB = String.format( "jdbc:mysql://programpersonal.imobilizari.ro:3306/%s?useLegacyDateTimeCode=false&serverTimezone=America/New_York", "imobiliz_cursuri" );
//    public static final String USE_TABLE = "USE personal";

    private int nrCrt;
    private Object furnizor;
    private String nrFactura;
    private String valoare;
    private LocalDate dataFacturii;
    private LocalDate dataContabilizarii;
    private Object contract;
    private Object contInv;
    private Object contFz;
    private Object nrProiect;
    private Object deviz;
    private Object org;
    private Object respProiect;
    private Object alegeColumn;
    private String addContract;
    private String descriereFactura;

    // bugetcontract
    private String CUIfurnizor;
    private CharSequence adresa;
    private CharSequence nrContract;
    private LocalDate dataContract ;
    private CharSequence valInitiala;

//pentru adaugare din pag de adminFZ inbugetContract
    public Investitii ( CharSequence characters, String text, CharSequence characters1, CharSequence characters2, LocalDate value, CharSequence characters3 ) {
            this.furnizor=characters;
            this.CUIfurnizor=text;
            this.adresa =characters1;
            this.nrContract =characters2;
            this.dataContract=value;
            this.valInitiala=characters3;
        }

    public String getCUIfurnizor() {
        return CUIfurnizor;
    }

    public void setCUIfurnizor(String CUIfurnizor) {
        this.CUIfurnizor = CUIfurnizor;
    }

    public CharSequence getAdresa() {
        return adresa;
    }

    public void setAdresa(CharSequence adresa) {
        this.adresa = adresa;
    }

    public void setNrContract(CharSequence nrContract) {
        this.nrContract = nrContract;
    }

    public CharSequence getNrContract() {
        return nrContract;
    }

    public LocalDate getDataContract() {
        return dataContract;
    }

    public void setDataContract(LocalDate dataContract) {
        this.dataContract = dataContract;
    }

    public CharSequence getValInitiala() {
        return valInitiala;
    }

    public void setValInitiala(CharSequence valInitiala) {
        this.valInitiala = valInitiala;
    }



    //constructor pentru Stage4PIF tabel1
    public Investitii ( String furnizor, String nrFactura, String contInv, String valoare, int nrCrt ) {
        this.furnizor = furnizor;
        this.nrFactura = nrFactura;
        this.contInv=contInv;
        this.valoare = valoare;
        this.nrCrt=nrCrt;
    }
    //constructor pentru Stage4PIF tabel2
//    public Investitii ( String furnizor, String nrFactura, String valoare ) {
//        this.furnizor = furnizor;
//        this.nrFactura=nrFactura;
//        this.valoare=valoare;
//    }

    public Investitii ( String furnizor, String nrFactura, String valoare, int nrCrt ) {
        this.furnizor = furnizor;
        this.nrFactura = nrFactura;
        this.valoare = valoare;
        this.nrCrt=nrCrt;
    }

    public Investitii ( String addContract ) {
        this.addContract = addContract;

    }


    //pentruStage4-extragere
    public Integer getNrCrt () {
        return nrCrt;
    }

    public Object getAlegeColumn () {return alegeColumn;    }

    public void setAlegeColumn (Object alegeColumn ) {this.alegeColumn = alegeColumn;}

    public Object getFurnizor () {
        return furnizor;
    }

    public void setFurnizor ( Object furnizor ) {
        this.furnizor = furnizor;
    }

    public String getNrFactura () {
        return nrFactura;
    }

    public void setNrFactura ( String nrFactura ) {
        this.nrFactura = nrFactura;
    }

    public String getValoare () {
        return valoare;
    }

    public void setValoare ( String valoare ) {
        this.valoare = valoare;
    }

    public LocalDate getDataFacturii () {
        return dataFacturii;
    }

    public void setDataFacturii ( LocalDate dataFacturii ) {
        this.dataFacturii = dataFacturii;
    }

    public LocalDate getDataContabilizarii () {
        return dataContabilizarii;
    }

    public void setDataContabilizarii ( LocalDate dataContabilizarii ) {
        this.dataContabilizarii = dataContabilizarii;
    }

    public Object getContract () {
        return contract;
    }

    public void setContract ( Object contract ) {
        this.contract = contract;
    }

    public Object getContInv () {
        return contInv;
    }

    public void setContInv ( Object contInv ) {
        this.contInv = contInv;
    }

    public Object getContFz () {
        return contFz;
    }

    public void setContFz ( Object contFz ) {
        this.contFz = contFz;
    }

    public Object getNrProiect () {
        return nrProiect;
    }

    public void setNrProiect ( Object nrProiect ) {
        this.nrProiect = nrProiect;
    }

    public Object getRespProiect () {
        return respProiect;
    }

    public void setRespProiect ( Object respProiect ) {
        this.respProiect = respProiect;
    }

    public Object getDeviz () {
        return deviz;
    }

    public void setDeviz ( Object deviz ) {
        this.deviz = deviz;
    }

    public Object getOrg () {
        return org;
    }

    public void setOrg ( Object org ) {
        this.org = org;
    }

    public String getDescriereFactura () {return descriereFactura;}

    public void setDescriereFactura ( String descriereFactura ) {
        this.descriereFactura = descriereFactura;
    }


    //constructor pt ctrl1StageIntro
    public Investitii ( Object furnizor, String nrfactura, String valoare, LocalDate dataFacturii, LocalDate dataContabilizarii, Object contract, Object ctInv, Object ctFz,  Object respProj, Object deviz, Object org, Object  nrProj, String descriereFactura ) {
        this.furnizor=furnizor;
        this.nrFactura = nrfactura;
        this.valoare= valoare;
        this.dataFacturii=dataFacturii;
        this.dataContabilizarii = dataContabilizarii;
        this.contract=contract;
        this.contInv=ctInv;
        this.contFz=ctFz;
        this.nrProiect=nrProj;
        this.deviz=deviz;
        this.org=org;
        this.respProiect=respProj;
        this.descriereFactura=descriereFactura;
    }
    // constructor pentru popularea listei definite in CtrlStage1Intro
    public Investitii ( Object furnizor, String nrFactura, String valoare, Object contInv, Object nrProiect, Object respProiect, String dataContabilizarii ) {
        this.furnizor=furnizor;
        this.nrFactura =  nrFactura;
        this.valoare= valoare;
        this.contInv=contInv;
        this.nrProiect= nrProiect;
        this.respProiect =respProiect;
        this.dataContabilizarii= LocalDate.parse(dataContabilizarii);
    }
    // constructor pt rapoarte
    public Investitii ( String furnizor, String nrFactura, String valoare, String dataContabilizarii, String respProiect, String contract, String contInv, String contFz, String nrProiect, String org ) {
        this.furnizor=furnizor;
        this.valoare= valoare;
        this.nrFactura=nrFactura;
        this.dataContabilizarii= LocalDate.parse(dataContabilizarii);
        this.respProiect =respProiect;
        this.contract = contract;
        this.contInv=contInv;
        this.contFz = contFz;
        this.nrProiect= nrProiect;
        this.org= org;
    }

}
