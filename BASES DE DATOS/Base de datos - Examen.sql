-- SECUENCIAS
CREATE SEQUENCE  S_ID_RESPONSABLE
MINVALUE 1 
MAXVALUE 99999 
INCREMENT BY 1 
START WITH 10
NOORDER  
NOCYCLE  
NOKEEP  
NOSCALE  
GLOBAL ;

CREATE SEQUENCE  S_ID_MASCOTA
MINVALUE 1 
MAXVALUE 99999 
INCREMENT BY 1 
START WITH 10
NOORDER  
NOCYCLE  
NOKEEP  
NOSCALE  
GLOBAL ;


-- TABLAS

CREATE TABLE RESPONSABLE (
    ID_RESPONSABLE NUMBER,
    NOMBRE NVARCHAR2(50),
    CONTACTO NVARCHAR2(10),
    VETERINARIA_ID NUMBER,
    CONSTRAINT PK_ID_RESPONSABLE PRIMARY KEY(ID_RESPONSABLE)
);

CREATE TABLE MASCOTA(
    ID_MASCOTA NUMBER,
    NOMBRE NVARCHAR2(20),
    RAZA NVARCHAR2(20),
    EDAD NUMBER,
    RAZON_CITA NVARCHAR2(100),
    CLIENTE_ID NUMBER,
    RESPONSABLE_ID NUMBER,
    VETERINARIA_ID NUMBER,
    CONSTRAINT PK_ID_MASCOTA PRIMARY KEY(ID_MASCOTA)
);



-- POPULAR RESPONSABLES
INSERT INTO RESPONSABLE VALUES(1, 'ASIS DURAN', '7777222277', 1);
INSERT INTO RESPONSABLE VALUES(2, 'ALBERTO CASTRO', '333388882', 1);
INSERT INTO RESPONSABLE VALUES(3, 'MARIA FERRA', '990099900', 2);
INSERT INTO RESPONSABLE VALUES(4, 'ELBERT MARTINEZ', '111000022', 3);
INSERT INTO RESPONSABLE VALUES(5, 'MONICA MORALES', '333888449', 3);
INSERT INTO RESPONSABLE VALUES(6, 'PATRICIO ALCALA', '333888449', 1);
COMMIT;

-- POPULA MASCOTA
INSERT INTO MASCOTA VALUES(1, 'BRUNO', 'DACHSUND', 8, 'VOMITO', 1, 4, 3);
INSERT INTO MASCOTA VALUES(2, 'KOKO', 'SCHNAUZER', 2, 'ANÁLISIS GENERALES', 3, 2, 1);
COMMIT;