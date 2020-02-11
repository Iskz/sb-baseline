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

import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;

import java.util.Map;

/**
 * An extension to the default JWT claims to enhance the token
 */
public class ClaimsVerifierConfig implements JwtClaimsSetVerifier {
  @Override
  public void verify(Map<String, Object> claims) throws InvalidTokenException {
    final String username = (String) claims.get("username");
    if ((username == null) || (username.length() == 0)) {
      throw new InvalidTokenException("username claim is empty");
    }
  }
}
