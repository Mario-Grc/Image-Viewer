package software.ulpgc.imageviewer.control;

public class PreviousImageCommand implements Command {
    private final ImagePresenter presenter;

    public PreviousImageCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(presenter.getImage().previous());
    }
}
