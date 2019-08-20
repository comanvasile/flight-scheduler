import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDelayComponent } from './add-delay.component';

describe('AddDelayComponent', () => {
  let component: AddDelayComponent;
  let fixture: ComponentFixture<AddDelayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddDelayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDelayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
