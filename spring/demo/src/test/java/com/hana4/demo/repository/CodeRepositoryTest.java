package com.hana4.demo.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import com.hana4.demo.entity.Code;
import com.hana4.demo.entity.CodeInfo;
import com.hana4.demo.entity.SubCode;
import com.hana4.demo.entity.User;

@DataJpaTest //실제 데이터 안들어가는 테스트 - ㅔpoolsistandse
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @SpringBootTest
public class CodeRepositoryTest {
	@Autowired
	CodeRepository codeRepository;

	@Autowired
	CodeInfoRepository codeInfoRepository;

	@Autowired
	SubCodeRepository subCodeRepository;

	private final static int ID = 2;

	@Test
	void codeUsersTest() {
		Code code = getCode();
		System.out.println("code = " + code);
		assertThat(code.getCodeUsers()).isNotNull();

		User user1 = new User("Hong11");
		User user2 = new User("Hong22");
		List<User> users = Arrays.asList(user1, user2);
		code.setCodeUsers(users);
		codeRepository.save(code);

	}

	@Test
	void addCodeWithSubCodeTest() {
		Code code = new Code();
		code.setCodeName(getCodeName());
		codeRepository.save(code);
		assertThat(code.getId()).isGreaterThan(0);

		SubCode subCode = new SubCode();
		subCode.setValue(getCodeName());
		subCode.setCode(code);
		subCodeRepository.save(subCode);
		assertThat(subCode.getId()).isGreaterThan(0);
	}

	@Test
	void findCodeInfoTest() {
		// codeInfoRepository.findAll().forEach(System.out::println);
		// Optional<CodeInfo> optionalCodeInfo = codeInfoRepository.findById(1);
		// CodeInfo codeInfo = optionalCodeInfo.orElseThrow();
		// System.out.println("codeInfo = " + codeInfo);
		CodeInfo codeInfo = getCodeInfo();
		System.out.println("codeInfo = " + codeInfo);
		assertThat(codeInfo).isNotNull();

	}

	@Test
	void findCodeTest() {
		// Optional<Code> first = Optional.of(codeRepository.findAll().stream().findFirst().orElseThrow());
		// Optional<Code> optionalCode = codeRepository.findById(ID);
		// Code code = optionalCode.orElseThrow();
		// System.out.println("code = " + code);
		Code code = getCode();
		assertThat(code).isNotNull();
		assertThat(code.getCodeInfo()).isNotNull();
	}

	@Test
	void addCodeTest() {
		String codeName = getCodeName();
		Code code = new Code();
		code.setCodeName(codeName);
		Code saveCode = codeRepository.save(code);
		System.out.println("saveCode = " + saveCode);
		assertThat(saveCode.getId()).isGreaterThan(0);

		CodeInfo codeInfo = new CodeInfo();
		codeInfo.setInfo("전국의 지점 모든 타입");
		codeInfo.setCode(code);
		CodeInfo saveCodeInfo = codeInfoRepository.save(codeInfo);
		System.out.println("saveCodeInfo = " + saveCodeInfo);
		assertThat(codeInfo.getId()).isGreaterThan(0);

	}

	private Code getCode() {
		List<Code> codes = codeRepository.findFirstByOrderBy(PageRequest.of(0, 1));
		System.out.println("codes = " + codes);
		return codes.stream().findFirst().orElseThrow();
	}

	private CodeInfo getCodeInfo() {
		List<CodeInfo> codeInfos = codeInfoRepository.findFirstByOrderById(PageRequest.of(0, 1));
		return codeInfos.stream().findFirst().orElseThrow();
	}

	private static String getCodeName() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
