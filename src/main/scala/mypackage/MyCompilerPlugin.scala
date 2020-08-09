package mypackage

import dotty.tools.dotc.ast._
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.core.Decorators._
import dotty.tools.dotc.plugins._
import dotty.tools.dotc.transform._
import dotty.tools.dotc.typer.Inliner
import dotty.tools.dotc.core.Names._
import dotty.tools.dotc.core.Constants._
import dotty.tools.dotc.core.Symbols
import dotty.tools.dotc.typer._

class MyCompilerPlugin extends StandardPlugin with PluginPhase {
  val name = getClass.getSimpleName
  val phaseName = getClass.getSimpleName

  def init(options: List[String]) = this :: Nil

  override val runsBefore = Set(Staging.name)

  override def transformIf(tree: tpd.If)(using context: Context): tpd.Tree = {
    import untpd._

    context.typer.typed(Apply(Select(Select(Ident(termName("mypackage")), termName("MyHooks")), termName("ifThenElse")), List(
      tree.cond,
      tree.thenp,
      tree.elsep,
    )))
  }
}