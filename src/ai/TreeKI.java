package ai;


public abstract class TreeKI<T extends Game> implements Player<T> {
	private int depth;
	private Evaluator<T> e;
	private TurnTree<T> tree;
	
	public TreeKI(int depth, Evaluator<T> e) {
		super();
		this.depth = depth;
		this.e = e;
	}

	/*@Override
	public abstract GameState<T> setUp(GameState<T> gs);*/
	




	@Override
	public GameState<T> turn(GameState<T> gs) {
		// TODO Auto-generated method stub
		if(tree==null) {
			tree=new TurnTree<T>(gs, depth,e);
		}else {
			tree=tree.update(gs,depth);
		}
		return tree.bestTurn(e);
	}
	
	
}
