package me.ayonel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangyun
 * @create 2020-03-07 22:26
 **/
@RestController
@RequestMapping("/hello")
public class MainController {

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@GetMapping("/{name}")
	public Object queryJob(@PathVariable(name = "name") String name) {
		if (name != null && name.equalsIgnoreCase(activeProfile)) {
			throw new ServiceException(String.format("do not call me %s, error occurred!!!", activeProfile));
		}
		return new WebRestResult(0, "ok!", null);
	}

}
