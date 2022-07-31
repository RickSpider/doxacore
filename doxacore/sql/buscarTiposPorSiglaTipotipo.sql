select 
t.tipoid, 
t.tipo, 
t.descripcion 
from tipos t
left join Tipotipos tt on tt.tipotipoid = t.tipotipoid
where tt.sigla = ?1;