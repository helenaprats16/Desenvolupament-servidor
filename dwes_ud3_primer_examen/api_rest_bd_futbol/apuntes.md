/_ APUNTES _/

// @Column(nullable = false [define que el atributo es obligatorio u opcional false/true] ,
// name = "nombre_entidad" [se pone name si el atributo no esta igual escrito que en la base de datos] ,
// length = 200 [Longitud máxima (solo en String)] ,
// unique = false / true [¿Valor único en la tabla?])

// @NotNull(message = "mensaje error" [Asegura que el campo NO sea null - para cualquier tipo de dato])

// @NotBlank(message = "mensaje error") [Asegura que el String NO sea null, NO vacío y NO solo espacios - solo para String]

// @Size(min = 1, max = 200, message = "mensaje error") [Controla longitud mínima y máxima - para String o colecciones]

// @Positive(message = "mensaje error") [Asegura que el número sea mayor que 0 - para números]

@NoArgsConstructor //Genera automáticamente un constructor sin parámetros
@AllArgsConstructor //Genera automáticamente un constructor con todos los parámetros
@Data //Combina: @Getter, @Setter, @ToString. @EqualsAndHashCode, @RequiredArgsConstructor
@Entity //Indica que esta clase es una entidad JPA = La clase es una tabla de la bbdds
@Table(name = "ciudades") //Ponerle el nombre a la tabla [és Opcional però millor posar-ho sempre per no tindrer mal entessos]

@Id // Marca este campo como la clave primaria de la entidad
@GeneratedValue(strategy = GenerationType.IDENTITY) //La base de datos generará automaticamente el valor (auto-increment)
@Size(min=4, message="El nombre debe de tener un tamaño minimo de 4 carácteres")
@Column(nullable= true)// permite valores nulos en la columnda de la base de datos
//Definir los equipos con @OneToMany porque una ciudad puede tener muchos equipos:
@OneToMany(mappedBy="ciudad") //Define una relación uno a muchos (una ciudad muchos equipos)
//mappedBy="clave ajena": Indica que la relación es bidireccional y el dueño de la relación es el campo ciudad en la clase EquipoNombreDb
    @JoinColumn(name="ciudad") //define la foreign key [se refiere literalmente al nombre de la columna en la base de datos]



@IdClass(JugadorId.class)



-------------------------------


Classe : Controller

//Tote les classes de controller tindrar este metodo, que sera el que s'encarrege de mostrar la tabla amb tots els paràmetres que en este cas te ciudades

@GetMapping("/ciudades")
    public ResponseEntity<ListadoRespuesta<CiudadList>> getAllCiudades(
            /*Llig el paràmetre de la url
                nombre -> filter opcional (no s'està usant en el codi, però està preparat)
                page -> número de pàgina (per defecte es la 0)
                size -> número d'elements per pàgina (per defecte 3)
                sort -> camp + direccio per a que es mostre (id,asc per defecte) */

            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        /*Crea un objecte Pageable amb :
            - Quina pàgina estem
            - Quants elements mostrar
            - Com ordernar (ex: "nombre,asc" [per nom ascendentment])
        */      
        Pageable pageable = PaginationHelper.createPageable(page, size, sort);


        //Recuperar los datos del service
        /*retorna un objecte PaginaDto amb:
            -pàgina actual
            -tamany
            -total d’elements
            -total de pàgines
            -llista de ciutats simplificades (CiudadList)        
        */
        PaginaDto<CiudadList> paginaCiudadList = ciudadService.findAllPageCiudadList(pageable);
        
        //Preprar respuesta
        ListadoRespuesta<CiudadList> response = new ListadoRespuesta<>(
            paginaCiudadList.getNumber(),
            paginaCiudadList.getSize(),
            paginaCiudadList.getTotalElements(),
            paginaCiudadList.getTotalPages(),
            paginaCiudadList.getContent());

        //Devolver Respuesta
        return ResponseEntity.ok(response);
    }



