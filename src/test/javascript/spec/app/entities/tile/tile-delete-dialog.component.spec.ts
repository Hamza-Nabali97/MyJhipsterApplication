/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterappTestModule } from '../../../test.module';
import { TileDeleteDialogComponent } from 'app/entities/tile/tile-delete-dialog.component';
import { TileService } from 'app/entities/tile/tile.service';

describe('Component Tests', () => {
    describe('Tile Management Delete Component', () => {
        let comp: TileDeleteDialogComponent;
        let fixture: ComponentFixture<TileDeleteDialogComponent>;
        let service: TileService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterappTestModule],
                declarations: [TileDeleteDialogComponent]
            })
                .overrideTemplate(TileDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TileDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TileService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
