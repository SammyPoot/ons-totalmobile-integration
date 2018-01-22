package com.qa.ons.totalmobile_stub;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
class MethodTests {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new MessageQueue()).build();
    }

    @Test
    public void testSayHelloWorld() throws Exception {
        this.mockMvc.perform(get("/").accept(MediaType.parseMediaType("application/soap;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/soap"));

    }
}
