use banana;

create table produto(
	idProduto int not null auto_increment,
	descricao varchar(200) not null,
	quantidade int,
	preco double,
	online boolean,
	primary key (idProduto)
);

desc produto;
select * from produto;