/** テーブル初期定義 **/

/** postgresに移動 **/
\c postgres

/**  login_info **/
CREATE TABLE public.t_login_info (uid SERIAL NOT NULL,
                                  mailaddress VARCHAR(355) NOT NULL,
                                  password VARCHAR(50) NOT NULL,
                                  login_date_last TIMESTAMP,
                                  PRIMARY KEY(uid), UNIQUE(mailaddress));
/** user_info **/
CREATE TABLE public.t_user_info (uid INTEGER NOT NULL,
                                 name VARCHAR(20) NOT NULL,
                                 u_type_id INTEGER NOT NULL,
                                 auth BOOLEAN NOT NULL,
                                 join_date TIMESTAMP,
                                 graduate_date TIMESTAMP,
                                 profile_picture_path VARCHAR(255),
                                 PRIMARY KEY(uid));
/** movie_list **/
CREATE TABLE public.t_movie_list (movie_id SERIAL NOT NULL,
                                 title VARCHAR(50) NOT NULL,
                                 m_type_id INTEGER NOT NULL,
                                 uid INTEGER,
                                 movie_url VARCHAR(255) NOT NULL,
                                 release BOOLEAN NOT NULL,
                                 create_date TIMESTAMP NOT NULL,
                                 release_date TIMESTAMP NOT NULL,
                                 PRIMARY KEY(movie_id));
/** user_type  **/
CREATE TABLE public.t_user_type (u_type_id INTEGER NOT NULL,
                                 u_type_name VARCHAR(20) NOT NULL,
                                 PRIMARY KEY(u_type_id));
/** movie_type **/
CREATE TABLE public.t_movie_type (m_type_id INTEGER NOT NULL,
                                  m_type_name VARCHAR(50) NOT NULL,
                                  PRIMARY KEY(m_type_id));
