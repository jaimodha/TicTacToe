    create table GamePlayers (
        id int4 not null,
        email varchar(255),
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    );

    create table Games (
        id int4 not null,
        end_time timestamp,
        ai_check boolean,
        is_completed boolean,
        save_time timestamp,
        start_time timestamp,
        game_status varchar(255),
        Player2_id int4,
        player1_id int4,
        primary key (id)
    );

    create table bord_entries (
        Games_id int4 not null,
        board_entries varchar(255)
    );

    create table played_games (
        GamePlayers_id int4 not null,
        playedgames_id int4 not null
    );

    create table saved_games (
        GamePlayers_id int4 not null,
        savedgames_id int4 not null
    );


    alter table GamePlayers 
        add constraint UK_rafsfyoffg099ibln3h160651 unique (username);

    alter table played_games 
        add constraint UK_ta3323e7vwe3tofqcnepn1n2q unique (playedgames_id);

    alter table saved_games 
        add constraint UK_nkbj8g801nspermn8dw9mrham unique (savedgames_id);

    alter table Games 
        add constraint FK_euwnt6ap4guvamjwrh49erh77 
        foreign key (Player2_id) 
        references GamePlayers;

    alter table Games 
        add constraint FK_q95k99gewy3p9xpew4oha5jji 
        foreign key (player1_id) 
        references GamePlayers;

    alter table bord_entries 
        add constraint FK_trmfniyv92adu292ytlojnpo8 
        foreign key (Games_id) 
        references Games;

    alter table played_games 
        add constraint FK_ta3323e7vwe3tofqcnepn1n2q 
        foreign key (playedgames_id) 
        references Games;

    alter table played_games 
        add constraint FK_o03tktbyt50e3vx298jg8cjpa 
        foreign key (GamePlayers_id) 
        references GamePlayers;

    alter table saved_games 
        add constraint FK_nkbj8g801nspermn8dw9mrham 
        foreign key (savedgames_id) 
        references Games;

    alter table saved_games 
        add constraint FK_cswd10e4cmgn8m88vp69rk74v 
        foreign key (GamePlayers_id) 
        references GamePlayers;

    create sequence hibernate_sequence START WITH 100;

    create table authorities (
    username    varchar(255) not null references GamePlayers(username),
    authority   varchar(255) default 'ROLE_USER'
	);
    
    
insert into GamePlayers values (1,'cysun@localhost.localdomain', 'abcd','cysun');
insert into authorities values('cysun', 'ROLE_USER');

insert into Games values(1, '2014-05-23 19:43:51.733', true, true, null, '2014-05-23 19:43:47.892', 'PLAYER1_WINS', Null, 1);
insert into Games values(2, '2014-05-23 19:43:51.733', true, true, null, '2014-05-23 19:43:47.892', 'AI_WINS', Null, 1);
insert into Games values(3, null, true, false, '2014-05-23 19:43:51.733', '2014-05-23 19:43:47.892', 'Saved', Null, 1);


insert into played_games values(1,1);
insert into played_games values(1,2);
insert into played_games values(1,3);
insert into saved_games values(1,3);



insert into bord_entries values(3,'x');
insert into bord_entries values(3,'_');
insert into bord_entries values(3,'_');
insert into bord_entries values(3,'o');
insert into bord_entries values(3,'_');
insert into bord_entries values(3,'_');
insert into bord_entries values(3,'_');
insert into bord_entries values(3,'_');
insert into bord_entries values(3,'_');



CREATE FUNCTION insertdata() returns trigger as $testref$
    BEGIN
    IF (TG_OP='INSERT') THEN
      INSERT INTO authorities (username) values (NEW.username);
      return NEW;
    END IF;
    END;
    $testref$ LANGUAGE plpgsql;
    CREATE TRIGGER testref AFTER INSERT ON GamePlayers
  	FOR EACH ROW 
  	EXECUTE PROCEDURE insertdata();