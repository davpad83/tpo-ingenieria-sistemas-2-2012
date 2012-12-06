insert into oficinadeventa (id, nombre, direccion, ip, puerto, nombrecolaremito)
values (7, 'Que es junit?', 'vivo en las nubes', '172.16.163.46',1099,'EnviarRemito')

insert into rodamiento (codigoSKF, marca, pais, stock)
values ('NJ 208 EMC3', 'FAG', 'Francia',0)

insert into itemlista (precio, rodamiento_id, items_idLista)
values (10.0, 37,3)

insert into ListaPrecios (nombre,vigenciaDesde,vigenciahasta,proveedor_id)
values ("Plus",'2012-09-09','2013-09-09',2)

delete from rodamiento where id= 27
delete from itemlista where id = 42

update itemlista set precio= 7.1 where id=13

select * from rodamiento
select * from ListaPrecios
select * from proveedor
select * from itemlista
select * from OficinaDeVenta
select * from cotizacion
select * from ItemRodamiento
select * from remito
select * from ItemRemito
select * from ordendecompra
select * from PedidoDeAbastecimiento

/*Cotizacion sin Marca*/

SELECT * FROM ItemLista IL
INNER JOIN rodamiento ROD on (IL.rodamiento_id = ROD.id) 
WHERE IL.precio =

        (SELECT distinct MIN(IL2.precio)
         FROM ItemLista IL2
             inner join rodamiento ROD2 on (IL2.rodamiento_id = ROD2.id)
	      WHERE ROD2.codigoSKF = ROD.codigoSKF
	        AND ROD2.pais = ROD.pais
		    AND ROD2.marca = ROD.marca
	 GROUP BY ROD2.marca)
	 
AND rod.codigoSKF = '6200 ZZ'
AND rod.pais = 'Reino unido'



/*Cotizacion con Marca*/

SELECT * FROM ItemLista IL
INNER JOIN rodamiento ROD on (IL.rodamiento_id = ROD.id) 
WHERE IL.precio =

(SELECT MIN(IL2.precio) 
FROM ItemLista IL2 
      inner join rodamiento ROD2 on (IL2.rodamiento_id = ROD2.id)
	    WHERE ROD2.codigoSKF = ROD.codigoSKF
		  AND ROD2.pais = ROD.pais
          AND ROD2.marca = ROD.marca)
          
AND rod.codigoSKF = 'NJ 208 EMC3'
AND rod.pais = 'Francia'
AND rod.marca = 'ZKL'

/*Buscar Tiempo de entrega con un id de item lista de precios */

SELECT distinct P.tiempoDeEntrega
FROM ListaPrecios LP
        inner join ItemLista IL on LP.idLista = IL.items_idLista
        inner join Proveedor P on LP.proveedor_id = P.id
WHERE IL.id = 19


