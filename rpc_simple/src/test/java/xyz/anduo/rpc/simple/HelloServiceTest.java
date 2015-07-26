package xyz.anduo.rpc.simple;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xyz.anduo.rpc.client.RpcProxy;
import xyz.anduo.rpc.sieve.modules.simple.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-client.xml")
public class HelloServiceTest {

	@Autowired
	private RpcProxy rpcProxy;

	@Before
	public void init() {
		System.out.println("============================================");
	}

	@Test
	public void helloTest() {
		new Thread() {
			public void run() {
				HelloService helloService = rpcProxy.create(HelloService.class);
				String result = helloService.hello("World");
				Assert.assertEquals("Hello! World", result);
			}
		}.start();
	}
}
