CREATE TABLE element_templates (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    project_id UUID NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    schema JSONB NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT unique_name_for_project UNIQUE(project_id, name)
);

ALTER TABLE elements DROP CONSTRAINT unique_element_name_type_for_project;
ALTER TABLE elements DROP COLUMN element_type;
ALTER TABLE elements ADD COLUMN element_template_id UUID;
ALTER TABLE elements ALTER COLUMN element_template_id SET NOT NULL;
ALTER TABLE elements ADD CONSTRAINT fk_element_template FOREIGN KEY (element_template_id) REFERENCES element_templates(id) ON DELETE RESTRICT;
ALTER TABLE elements ADD CONSTRAINT unique_element_name_for_template UNIQUE(project_id, element_template_id, name);

