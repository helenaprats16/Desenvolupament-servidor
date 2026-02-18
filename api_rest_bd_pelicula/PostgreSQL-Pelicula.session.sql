SELECT tc.constraint_name, rc.delete_rule
FROM information_schema.referential_constraints rc
JOIN information_schema.table_constraints tc
  ON rc.constraint_name = tc.constraint_name
WHERE tc.table_name IN ('pelicula','valoracion');