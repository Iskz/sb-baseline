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

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;


public class TokenEnhancerConfig implements TokenEnhancer {

  public static final int RANDOM_ALPHABATIC_INT = 4;
  public static final String ADDITIONAL_INFO = "organization";

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                   OAuth2Authentication authentication) {
    final Map<String, Object> additionalInfo = new HashMap<>();
    additionalInfo.put(ADDITIONAL_INFO,
                  authentication.getName() + randomAlphabetic(RANDOM_ALPHABATIC_INT));
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
    return accessToken;
  }
}
