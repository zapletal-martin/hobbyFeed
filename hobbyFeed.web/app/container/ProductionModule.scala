package container

import javax.inject._
import com.google.inject.{AbstractModule}
import injectionTest._

class ProductionModule extends AbstractModule {
	protected def configure() {
      bind(classOf[IShouting]).to(classOf[TwoShout])
    }
}