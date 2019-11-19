create table rto(
rto_id numeric,
branch varchar2(255) unique,
pwd varchar2(255),
username varchar2(255)
);

alter table rto
add constraint rto_pk primary key (rto_id);

create table vehicle(
vehicle_no varchar2(255),
v_model varchar2(255),
v_owner varchar2(255),
owner_phone numeric,
alternate_phone numeric,
owner_address varchar2(255),
owner_email varchar2(255),
rto_branch varchar2(255),
insurance_company varchar2(255),
insurance_paid number(9,2)
);

alter table vehicle
add constraint vehicle_pk primary key (vehicle_no);

create table insurance_company(
insurance_id numeric,
insurance_name varchar2(255) unique,
insurance_norms varchar2(4000),
pwd varchar2(255)
);

alter table insurance_company
add constraint insurance_pk primary key (insurance_id);


create table accident_info(
vehicle_no varchar2(255),
accident_id varchar2(255),
insurance_company varchar2(255),
place varchar2(255),
damage varchar2(255)
);

alter table accident_info
add constraint accident_pk primary key (accident_id);


create table insured(
insured_id varchar2(255),
vehicle_no varchar2(255),
amount_details varchar2(255),
start_date varchar2(255),
expiry_date varchar2(255)
);

alter table insured
add constraint insured_pk primary key (insured_id);

alter table vehicle
add constraint vehicle_rto_fk foreign key (rto_branch) references rto(branch);

alter table vehicle
add constraint vehicle_insurance_fk foreign key (insurance_company) references insurance_company(insurance_name);

alter table accident_info
add constraint accident_vehicle_fk foreign key (vehicle_no) references vehicle(vehicle_no);

alter table accident_info
add constraint accident_insurance_fk foreign key (insurance_company) references insurance_company(insurance_name);

alter table insured
add constraint insured_vehicle_fk foreign key (vehicle_no) references vehicle(vehicle_no);

Create sequence accidentGenerator
start with 4444
increment by 1
nocycle
nocache;

create view connection as (select v.vehicle_no,insurance_company from vehicle v,insured i where v.vehicle_no=i.vehicle_no);

