import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DocumentoDialogComponent} from './documento-dialog.component';
import {AppModule} from "../../app.module";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

describe('DocumentoDialogComponent', () => {
  let component: DocumentoDialogComponent;
  let fixture: ComponentFixture<DocumentoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        AppModule
      ], providers: [
        {provide: MAT_DIALOG_DATA, useValue: {}},
        {provide: MatDialogRef, useValue: {}}
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(DocumentoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
