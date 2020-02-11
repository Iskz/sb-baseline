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

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Order(Ordered.HIGHEST_PRECEDENCE + 100)
@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

  /**
   * Main Security configuration file for the Orchestrator application
   *
   * @param http - The HTTP Security
   * @throws Exception
   */
  @Override
  public void configure(final HttpSecurity http) throws Exception {
//     @formatter:off
    http.authorizeRequests().antMatchers("/login").permitAll()
            .antMatchers("/oauth/token/revokeById/**").permitAll()
            .antMatchers("/oauth/**").permitAll()
            .antMatchers("/user/**").permitAll()
            .antMatchers("/images/**").permitAll()
            .antMatchers("/actuator/**").permitAll()
            .antMatchers("/**").permitAll()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .anyRequest().authenticated();

    http.formLogin().disable();
    http.csrf().disable();
//     @formatter:on
  }
}
