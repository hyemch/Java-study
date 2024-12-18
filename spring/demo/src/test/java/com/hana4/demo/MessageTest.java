package com.hana4.demo;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

@SpringBootTest
public class MessageTest {
	@Autowired
	MessageSource msgsrc;

	@Test
	void siteMessageSource() {
		String title = msgsrc.getMessage("site.title", null, Locale.KOREAN);
		assertThat(title).isEqualTo("데모앱");
		String title2 = msgsrc.getMessage("site.title", null, Locale.ENGLISH);
		assertThat(title2).isEqualTo("DemoApplication");
		System.out.println(title2);

		String description = msgsrc.getMessage("site.description", new Object[] {"테스트"}, Locale.KOREAN);
		assertThat(description).isEqualTo("데모::테스트");

	}

}
