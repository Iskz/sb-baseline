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

package com.aac.nba.converter;

import com.aac.nba.Constants;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GSON serialiser/deserialiser for converting {@link LocalDateTime} objects.
 */
public class LocalDateTimeConverter implements JsonSerializer<LocalDateTime>,
                                               JsonDeserializer<LocalDateTime>
{
  /** Formatter. */
  private static final DateTimeFormatter FORMATTER =
                            DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
  /**
   * Gson invokes this call-back method during serialization when it encounters a field of the
   * specified type. <p>
   *
   * In the implementation of this call-back method, you should consider invoking
   * {@link JsonSerializationContext#serialize(Object, Type)} method to create JsonElements for any
   * non-trivial field of the {@code src} object. However, you should never invoke it on the
   * {@code src} object itself since that will cause an infinite loop (Gson will call your
   * call-back method again).
   *
   * @param src the object that needs to be converted to Json.
   * @param typeOfSrc the actual type (fully genericized version) of the source object.
   * @return a JsonElement corresponding to the specified object.
   */
  @Override
  public JsonElement serialize(LocalDateTime src,
                               Type typeOfSrc,
                               JsonSerializationContext context)
  {
    return new JsonPrimitive(FORMATTER.format(src));
  }

  /**
   * Gson invokes this call-back method during deserialization when it encounters a field of the
   * specified type. <p>
   *
   * In the implementation of this call-back method, you should consider invoking
   * {@link JsonDeserializationContext#deserialize(JsonElement, Type)} method to create objects
   * for any non-trivial field of the returned object. However, you should never invoke it on the
   * the same type passing {@code json} since that will cause an infinite loop (Gson will call your
   * call-back method again).
   *
   * @param json The Json data being deserialized
   * @param typeOfT The type of the Object to deserialize to
   * @return a deserialized object of the specified type typeOfT which is a subclass of {@code T}
   * @throws JsonParseException if json is not in the expected format of {@code typeOfT}
   */
  @Override
  public LocalDateTime deserialize(JsonElement json,
                                   Type typeOfT,
                                   JsonDeserializationContext context)
          throws JsonParseException
  {
    return FORMATTER.parse(json.getAsString(), LocalDateTime::from);
  }
}