package container

import javax.inject._
import com.google.inject.{AbstractModule}
import injectionTest._
import authentication._

class DevelopmentModule extends AbstractModule{
	protected def configure() {
      bind(classOf[IShouting]).to(classOf[OneShout])
      bind(classOf[IAuthenticationService]).to(classOf[FakeAuthenticationService])
    }
}
