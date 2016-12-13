--Userconnection
CREATE TABLE userconnection
(
  userid         CHARACTER VARYING(255) NOT NULL,
  providerid     CHARACTER VARYING(255) NOT NULL,
  provideruserid CHARACTER VARYING(255) NOT NULL,
  rank           INTEGER                NOT NULL,
  displayname    CHARACTER VARYING(255),
  profileurl     CHARACTER VARYING(512),
  imageurl       CHARACTER VARYING(512),
  accesstoken    CHARACTER VARYING(512) NOT NULL,
  secret         CHARACTER VARYING(512),
  refreshtoken   CHARACTER VARYING(512),
  expiretime     BIGINT,
  CONSTRAINT userconnection_pkey PRIMARY KEY (userid, providerid, provideruserid)
);

CREATE UNIQUE INDEX userconnectionrank
  ON userconnection
  USING BTREE
  (userid COLLATE pg_catalog."default", providerid COLLATE pg_catalog."default", rank);

--User
CREATE TABLE public.user
(
  id               BIGINT                 NOT NULL,
  email            CHARACTER VARYING(250) NOT NULL,
  first_name       CHARACTER VARYING(100) NOT NULL,
  last_name        CHARACTER VARYING(100) NOT NULL,
  role             CHARACTER VARYING(20)  NOT NULL,
  sign_in_provider CHARACTER VARYING(20),
  location         CHARACTER VARYING(255),
  photo_uri        CHARACTER VARYING(350),
  CONSTRAINT user_primary_key PRIMARY KEY (id),
  CONSTRAINT user_email_unique_key UNIQUE (email)
);

CREATE SEQUENCE user_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

--Patients
CREATE TABLE public.patient
(
  id                 BIGINT                 NOT NULL,
  names              CHARACTER VARYING(100) NOT NULL,
  last_name1         CHARACTER VARYING(100) NOT NULL,
  last_name2         CHARACTER VARYING(100),
  birthday           DATE                   NOT NULL,
  marital_status     CHARACTER VARYING(25)  NOT NULL,
  activity           CHARACTER VARYING(255),
  husband_name       CHARACTER VARYING(255),
  husband_birthday   DATE,
  husband_activity   CHARACTER VARYING(255),
  number_of_children INTEGER                NOT NULL,
  address            CHARACTER VARYING(300),
  phone_number       CHARACTER VARYING(80),
  email              CHARACTER VARYING(250),
  register_date      TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_DATE,

  CONSTRAINT patient_primary_key PRIMARY KEY (id)
);

CREATE SEQUENCE patient_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

--PatientsByUser
CREATE TABLE public.patient_by_user
(
  id         BIGINT NOT NULL,
  user_id    BIGINT NOT NULL,
  patient_id BIGINT NOT NULL,
  notes      TEXT,

  CONSTRAINT patient_by_user_primary_key PRIMARY KEY (id),
  CONSTRAINT fk_patient_by_user_user FOREIGN KEY (user_id) REFERENCES public.user,
  CONSTRAINT fk_patient_by_user_patient FOREIGN KEY (patient_id) REFERENCES public.patient
);

CREATE SEQUENCE patient_by_user_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

--Partners
CREATE TABLE public.partner
(
  id           BIGINT                 NOT NULL,
  full_name    CHARACTER VARYING(200) NOT NULL,
  institution  CHARACTER VARYING(100) NOT NULL,
  phone_number CHARACTER VARYING(80),
  email        CHARACTER VARYING(250),
  user_id      BIGINT                 NOT NULL,

  CONSTRAINT partner_primary_key PRIMARY KEY (id),
  CONSTRAINT fk_partner_user FOREIGN KEY (user_id) REFERENCES public.user
);

CREATE SEQUENCE partner_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

--Specialty
CREATE TABLE public.specialty
(
  id      BIGINT                 NOT NULL,
  name    CHARACTER VARYING(200) NOT NULL,
  user_id BIGINT                 NOT NULL,

  CONSTRAINT specialty_primary_key PRIMARY KEY (id),
  CONSTRAINT fk_specialty_user FOREIGN KEY (user_id) REFERENCES public.user
);

--PartnerSpecialty
CREATE TABLE public.partner_specialty
(
  id           BIGINT NOT NULL,
  partner_id   BIGINT NOT NULL,
  specialty_id BIGINT NOT NULL,

  CONSTRAINT partner_specialty_primary_key PRIMARY KEY (id),
  CONSTRAINT fk_partner_specialty_partner FOREIGN KEY (partner_id) REFERENCES public.partner,
  CONSTRAINT fk_partner_specialty_specialty FOREIGN KEY (specialty_id) REFERENCES public.specialty
);

CREATE SEQUENCE partner_specialty_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

--ProcedureType
CREATE TABLE public.procedure_type
(
  id   BIGINT NOT NULL,
  name BIGINT NOT NULL,

  CONSTRAINT procedure_type_primary_key PRIMARY KEY (id)
);

CREATE SEQUENCE procedure_type_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

--PatientProcedure
CREATE TABLE public.patient_procedure
(
  id                BIGINT                      NOT NULL,
  procedure_type_id BIGINT                      NOT NULL,
  patient_id        BIGINT                      NOT NULL,
  created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_DATE,
  closed_at         TIMESTAMP WITHOUT TIME ZONE,

  CONSTRAINT patient_procedure_primary_key PRIMARY KEY (id),
  CONSTRAINT fk_patient_procedure_procedure_type FOREIGN KEY (procedure_type_id) REFERENCES public.procedure_type,
  CONSTRAINT fk_patient_procedure_patient FOREIGN KEY (patient_id) REFERENCES public.patient
);

CREATE SEQUENCE patient_procedure_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

--PatientProcedureByUser
CREATE TABLE public.patient_procedure_by_user
(
  id                   BIGINT NOT NULL,
  user_id              BIGINT NOT NULL,
  patient_procedure_id BIGINT NOT NULL,

  CONSTRAINT patient_procedure_by_user_by_user_primary_key PRIMARY KEY (id),
  CONSTRAINT fk_patient_procedure_by_user_user FOREIGN KEY (user_id) REFERENCES public.user,
  CONSTRAINT fk_patient_procedure_by_user_patient FOREIGN KEY (patient_procedure_id) REFERENCES public.patient_procedure
);

CREATE SEQUENCE patient_procedure_by_user_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

--MedicalRecord
CREATE TABLE public.medical_record
(
  patient_id           BIGINT NOT NULL,
  aht_dm               CHARACTER VARYING(500),
  aht_hta              CHARACTER VARYING(500),
  aht_oncologicos      CHARACTER VARYING(500),
  aht_otros            CHARACTER VARYING(500),
  apnp_tabaquismo      CHARACTER VARYING(500),
  apnp_alcoholismo     CHARACTER VARYING(500),
  apnp_drogadiccion    CHARACTER VARYING(500),
  app_qx               CHARACTER VARYING(500),
  app_alergicos        CHARACTER VARYING(500),
  app_transfuncionales CHARACTER VARYING(500),
  app_traumaticos      CHARACTER VARYING(500),
  tipoyrh              CHARACTER VARYING(500),

  CONSTRAINT medical_record_primary_key PRIMARY KEY (patient_id),
  CONSTRAINT fk_medical_record_patient FOREIGN KEY (patient_id) REFERENCES public.patient
);

--------------------------------------------------------------------------
-----------------------------PATIENT HISTORY------------------------------
--------------------------------------------------------------------------

--Patient History
CREATE TABLE public.patient_history
(
  id                   BIGINT NOT NULL,
  id_patient_procedure BIGINT NOT NULL,
  revision_date        DATE   NOT NULL DEFAULT CURRENT_DATE,
  details              JSON   NOT NULL,

  CONSTRAINT patient_history_primary_key PRIMARY KEY (id),
  CONSTRAINT fk_patient_history_patient_procedure FOREIGN KEY (id_patient_procedure) REFERENCES public.patient_procedure
);

CREATE SEQUENCE patient_history_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

--------------------------------------------------------------------------
------------------------------PREGNANCY-----------------------------------
--------------------------------------------------------------------------

--pregnancy
CREATE TABLE public.pregnancy
(
  id_patient_procedure BIGINT NOT NULL,
  notes                TEXT,
  puerperium           JSON,
  pregnancyResult      JSON,

  CONSTRAINT pregnancy_primary_key PRIMARY KEY (id_patient_procedure),
  CONSTRAINT fk_pregnancy_patient_procedure FOREIGN KEY (id_patient_procedure) REFERENCES public.patient_procedure
);

CREATE TABLE public.pregnancy_revision
(
  id           BIGINT NOT NULL,
  id_pregnancy BIGINT NOT NULL,
  revisionDate DATE   NOT NULL DEFAULT CURRENT_DATE,
  details      JSON   NOT NULL,

  CONSTRAINT pregnancy_revision_primary_key PRIMARY KEY (id),
  CONSTRAINT fk_pregnancy_revision_pregnancy FOREIGN KEY (id_pregnancy) REFERENCES public.pregnancy
);

CREATE SEQUENCE pregnancy_revision_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

--------------------------------------------------------------------------
-----------------------------PAPANICOLAOU---------------------------------
--------------------------------------------------------------------------

--Papanicolau
CREATE TABLE public.papanicolau
(
  id_patient_procedure BIGINT NOT NULL,
  details              JSON   NOT NULL,

  CONSTRAINT papanicolau_primary_key PRIMARY KEY (id_patient_procedure),
  CONSTRAINT fk_papanicolau_patient_procedure FOREIGN KEY (id_patient_procedure) REFERENCES public.patient_procedure
);

--------------------------------------------------------------------------
-----------------------------EXAMINATION---------------------------------
--------------------------------------------------------------------------

--GynecologicalExamination
CREATE TABLE public.gynecological_examination
(
  id_patient_procedure BIGINT NOT NULL,
  details              JSON   NOT NULL,

  CONSTRAINT gynecological_examination_primary_key PRIMARY KEY (id_patient_procedure),
  CONSTRAINT fk_gynecological_examination_patient_procedure FOREIGN KEY (id_patient_procedure) REFERENCES public.patient_procedure
);

--BreastExamination
CREATE TABLE public.breast_examination
(
  id_patient_procedure BIGINT NOT NULL,
  details              JSON   NOT NULL,

  CONSTRAINT breast_examination_primary_key PRIMARY KEY (id_patient_procedure),
  CONSTRAINT fk_breast_examination_patient_procedure FOREIGN KEY (id_patient_procedure) REFERENCES public.patient_procedure
);

--BreastMark
CREATE TABLE public.breast_mark
(
  id                    BIGINT NOT NULL,
  id_breast_examination BIGINT NOT NULL,
  details               JSON   NOT NULL,

  CONSTRAINT breast_mark_primary_key PRIMARY KEY (id),
  CONSTRAINT fk_breast_mark_breast_examination FOREIGN KEY (id_breast_examination) REFERENCES public.breast_examination
);

CREATE SEQUENCE breast_mark_seq
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 1
CACHE 1;