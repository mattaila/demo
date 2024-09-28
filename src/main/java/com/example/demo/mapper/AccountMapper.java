package com.example.demo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.example.demo.dto.Account;

@Repository
@Mapper
public interface AccountMapper {

    @Select("select * from accounts")
    List<Account> findAll();

    @Select("select * from accounts where username = #{userName}")
    Account findOne(String userName);

    @Insert("insert into item (name, team, position) values (#{name}, #{team}, #{position})")
    @Options(useGeneratedKeys = true)
    void save(Account account);

    @Update("update item set name = #{name}, team = #{team}, position = #{position} where id = #{id}")
    void update(Account account);

    @Delete("delete from item where id = #{id}")
    void delete(Long id);
}
