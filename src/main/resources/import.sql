CREATE TABLE TBL_CIASTO AS SELECT * FROM CSVREAD('./MassBalanceCalculator/src/main/resources/tbl_ciasto.txt');
CREATE TABLE TBL_CIASTO_CALOSC AS SELECT * FROM CSVREAD('./MassBalanceCalculator/src/main/resources/tbl_ciasto_calosc.txt');
CREATE TABLE TBL_KREM_CALOSC AS SELECT * FROM CSVREAD('./MassBalanceCalculator/src/main/resources/tbl_krem_calosc.txt');
CREATE TABLE TBL_KREMY AS SELECT * FROM CSVREAD('./MassBalanceCalculator/src/main/resources/tbl_kremy.txt');
CREATE TABLE TBL_SUROWCE AS SELECT * FROM CSVREAD('./MassBalanceCalculator/src/main/resources/tbl_surowce.txt');
CREATE TABLE TBL_WYROBY AS SELECT * FROM CSVREAD('./MassBalanceCalculator/src/main/resources/tbl_wyroby.txt');
CREATE TABLE TBL_WYROBY_SKLAD AS SELECT * FROM CSVREAD('./MassBalanceCalculator/src/main/resources/tbl_wyroby_sklad.txt');
