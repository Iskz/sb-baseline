/*
 * (C) Copyright 2019 Axiata Analytics Center (https://axiata.com/).
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.aac.nba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    List<String> stringList = new ArrayList<>();
    stringList.add("/smooch/**");
    registry.addInterceptor(localeChangeInterceptor());
//    registry.addInterceptor(requestInterceptor)
//        .addPathPatterns(stringList);

    new HandlerInterceptor() {

    };
  }

  /**
   * This interceptor allows us to change the locale on a per-request basis
   *
   * @return a LocaleChangeInterceptor object
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName("language");
    return interceptor;
  }
}
