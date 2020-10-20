 create table customer(
        customer_id     serial  primary key,
        first_name      varchar(20),
        last_name       varchar(30),
        address         varchar(200),
        phone_number    varchar(15)
 );
  create table purchase(
        purchase_id              serial  primary key,
        artist_id                integer ,
        painting_id              integer,
        date_of_purchase         timestamp not null,
        price                   integer,
        constraint fk_puchase_artist_id foreign key (artist_id) references artist(artist_id),
         constraint fk_puchase_painting_id foreign key (painting_id) references painting(painting_id),
 );
 create table customer_purchase(
 customer_id    integer,
 purchase_id    integer,
 constraint pk_customer_purchase primary key(customer_id,purchase_id)
 
 
 );
 