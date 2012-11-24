insert into oficinadeventa (id, nombre, direccion, ip, puerto, nombrecolaremito)
values (7, 'Que es junit?', 'vivo en las nubes', '172.16.163.46',1099,'EnviarRemito')

insert into rodamiento (codigoSKF, marca, pais, stock)
values ('NJ 208 EMC3', 'FAG', 'Francia',0)


insert into rodamiento (precio, rodamiento_id, items_idLista)
values ('', 0,0)

insert into itemlista (precio, rodamiento_id, items_idLista)
values (10.0, 37,3)

SELECT * 
FROM ItemLista IL
     INNER JOIN rodamiento ROD on (IL.rodamiento_id = ROD.id) 
WHERE IL.precio = 
        (SELECT MIN(IL2.precio)
         FROM ItemLista IL2
             inner join rodamiento ROD2 on (IL2.rodamiento_id = ROD2.id)
	 WHERE ROD2.codigoSKF = ROD.codigoSKF
	        AND ROD2.pais = ROD.pais 
	        AND ROD2.marca = ROD.marca
	 GROUP BY ROD2.marca)
AND rod.codigoSKF = 'NJ 208 EMC3'
AND rod.pais = 'Francia'