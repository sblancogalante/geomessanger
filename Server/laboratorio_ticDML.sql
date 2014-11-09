-- Este es el documento con las actualizaciones a la BD, para generar algunos datos de entrada

-- Empleados

INSERT INTO `laboratorio_tic`.`Employees`
(
`document`,
`iD`,
`name`,	
`lastName`, 
`userName`,
`password`,
`location`,
`sector`,
`mail`,
`position`, 
`status`,
`admin`)
VALUES
(
"Cedula",
"5.062.081-0",
"Santiago",
"Blanco",
"sblanco",
"8db58c37c3abdbdc188996ea34b17b3f",
"Uruguay",
"Desarrollo",
"sblanco1@correo.um.edu.uy",
"Programador",
0,
1
);

INSERT INTO `laboratorio_tic`.`Employees`
(
`document`,
`iD`,
`name`,	
`lastName`, 
`userName`,
`password`,
`location`,
`sector`,
`mail`,
`position`, 
`status`,
`admin`)
VALUES
(
"Cedula",
"4.670.218-5",
"Luis",
"Gurmendez",
"lgurmendez",
"b328b777edaf5a550794e5a35567f08",
"Uruguay",
"Desarrollo",
"lgurmendez@correo.um.edu.uy",
"Programador",
0,
1
);

-- Tipos generales

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Uruguay"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Chile"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Argentina"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Brasil"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Colombia"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Ecuador"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Venezuela"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Paraguay"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Peru"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Bolivia"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Pais",
"Estados Unidos"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Comercial"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Ventas"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Recursos Humanos"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Marketing"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Gerencia"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Administracion"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Desarrollo"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Construccion"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Implantacion"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Implementacion"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Testing"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Mantenimiento"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Soporte"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Requerimientos"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Sector",
"Gestion de calidad"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Documento",
"Cedula"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Documento",
"Credencial"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Documento",
"Pasaporte"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Documento",
"Libreta de consucir"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Documento",
"Indocumentado"
);

INSERT INTO `laboratorio_tic`.`Types`
(
`type`,
`value`
)
VALUES
(
"Documento",
"Documento temporal"
);






