select c.ciudadid,
c.ciudad,
d.departamento,
p.pais
from ciudades 
left join departamentos d on d.departamentoid = c.departamentoid 
left join paises p on p.paisid = d.paisid
order by c.ciudadid asc;