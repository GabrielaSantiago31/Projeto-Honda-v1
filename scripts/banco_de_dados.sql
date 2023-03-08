create database loja_motos;

create table if not exists vendedores(
    id int auto_increment primary key,
    nome varchar(50) not null,
    cpf char(12) not null,
    sexo enum('M','F') not null,
    salario decimal (10,2) not null,
    ano_contratacao int not null,
    observacoes varchar(100)
) default charset = utf8;

create table if not exists motos(
    id int auto_increment primary key,
    marca varchar(50) not null,
    modelo varchar(50) not null,
    cor varchar(50) not null,
    cilindrada int not null,
    ano int not null,
    preco decimal (10,2) not null,
    quantidade int not null,
    observacoes varchar(100)
) default charset = utf8;

create table if not exists vendas(
    id int auto_increment primary key,
    quantidade int not null,
    total decimal(10,2) not null,
    data_hora datetime not null,
    pagamento varchar(50) not null,
    id_vendedores int not null,
    id_motos int not null
) default charset = utf8;

alter table vendas add constraints FK_vendedores foreign key (id_vendedores) references vendedores(id);
alter table vendas add constraints FK_motos foreign key (id_motos) references motos(id);