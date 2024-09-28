package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Account;
import com.example.demo.mapper.AccountMapper;

import jakarta.transaction.Transactional;
import java.util.Objects;

@Component
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
	// ユーザー情報テーブル用のリポジトリを注入
	@Autowired
	private AccountMapper accountMapper;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// DBからユーザー情報をSELECTする。
		// ユーザーが見つからない場合は例外をスローする
		Account account = accountMapper.findOne(username);
		if(Objects.isNull(account)){
			System.out.println("ユーザー:" + username + "が見つかりません。");
			throw new UsernameNotFoundException(username);
		}
		
		//有効期限が切れている場合は例外をスローする
		if(account.isExpired()) {
			System.out.println("ユーザー:" + username + "は有効期限が切れています");
			throw new UsernameNotFoundException(username);
		}
		
		// 見つかったユーザー情報により認証情報を生成する
		return User
				.withUsername(account.getUsername())
				.password(account.getPassword())
				.roles(account.getRole())
				.disabled(!account.isEnabled())
				.build();
	}
}
