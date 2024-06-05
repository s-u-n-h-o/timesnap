package com.example.timesnap.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository는 PagingAndSqortingRepository,QueryByExampleExecutor 인터페이스를 상속받고있다
//PagingAndSqortingRepository는 CrudRepository 인터페이스를 상속받고 있기때문에 기본적인 crud 메소드를 제공한다
public interface UserRepository extends JpaRepository<User ,Integer> {

    @Override
    <S extends User> S save(S entity);

}
